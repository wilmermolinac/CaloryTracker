package com.wamcstudios.calorytracker.tracker.domain.use_case

import com.wamcstudios.calorytracker.tracker.domain.model.MealType
import com.wamcstudios.calorytracker.tracker.domain.model.TrackableFood
import com.wamcstudios.calorytracker.tracker.domain.model.TrackedFood
import com.wamcstudios.calorytracker.tracker.domain.repository.TrackerRepository
import java.time.LocalDate
import javax.inject.Inject
import kotlin.math.roundToInt

class TrackFood @Inject constructor(private val repository: TrackerRepository) {

    suspend operator fun invoke(
        food: TrackableFood,
        amount: Int,
        mealType: MealType,
        date: LocalDate,
    ) {
        repository.insertTrackedFood(
            TrackedFood(
                name = food.name,
                carbs = ((food.carbsPer100g / 100f) * amount).roundToInt(),
                protein = ((food.proteinPer100g / 100f) * amount).roundToInt(),
                fat = ((food.fatPer100g / 100f) * amount).roundToInt(),
                imageUrl = food.imageUrl,
                mealType = mealType,
                amount = amount,
                date = date,
                calories = ((food.caloriesPer100g / 100f) * amount).roundToInt()
            )
        )
    }
}