package com.wamcstudios.calorytracker.tracker.data.repository

import com.wamcstudios.calorytracker.tracker.data.local.TrackerDao
import com.wamcstudios.calorytracker.tracker.data.mapper.toTrackableFood
import com.wamcstudios.calorytracker.tracker.data.mapper.toTrackedFood
import com.wamcstudios.calorytracker.tracker.data.mapper.toTrackerFoodEntity
import com.wamcstudios.calorytracker.tracker.data.remote.OpenFoodApi
import com.wamcstudios.calorytracker.tracker.data.remote.dto.Product
import com.wamcstudios.calorytracker.tracker.domain.model.TrackableFood
import com.wamcstudios.calorytracker.tracker.domain.model.TrackedFood
import com.wamcstudios.calorytracker.tracker.domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class TrackerRepositoryImpl @Inject constructor(
    private val dao: TrackerDao,
    private val api: OpenFoodApi,
) : TrackerRepository {
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int,
    ): Result<List<TrackableFood>> {

        return try {

            val searchDto = api.searchFood(query, page, pageSize)


            Result.success(
                //                Ignora cualquier elemento nulo que pueda ser devuelto por 'toTrackableFood()'
                searchDto.products
                    .filter {
                        val calculatedCalories =
                            it.nutriments.carbohydrates100g * 4f +
                                    it.nutriments.proteins100g * 4f +
                                    it.nutriments.fat100g * 9f

                        val lowerBound = calculatedCalories * 0.99f
                        val upperBound = calculatedCalories * 1.01f
                        it.nutriments.energyKcal100g in (lowerBound..upperBound)
                    }
                    .mapNotNull {
                        it.toTrackableFood()
                    })

        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }

    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        dao.insertTrackerFood(food.toTrackerFoodEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        dao.deleteTrackerFood(food.toTrackerFoodEntity())
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodsForDate(
            day = localDate.dayOfMonth,
            month = localDate.monthValue,
            year = localDate.year
        ).map { entities ->
            entities.map { entity ->
                entity.toTrackedFood()
            }
        }
    }
}