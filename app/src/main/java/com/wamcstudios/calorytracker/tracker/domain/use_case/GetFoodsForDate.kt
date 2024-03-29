package com.wamcstudios.calorytracker.tracker.domain.use_case

import com.wamcstudios.calorytracker.tracker.domain.model.TrackedFood
import com.wamcstudios.calorytracker.tracker.domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetFoodsForDate @Inject constructor(private val repository: TrackerRepository) {

    operator fun invoke(date: LocalDate):Flow<List<TrackedFood>>{
        return repository.getFoodsForDate(localDate = date)
    }
}