package com.wamcstudios.calorytracker.tracker.domain.use_case

import com.wamcstudios.calorytracker.tracker.domain.model.TrackedFood
import com.wamcstudios.calorytracker.tracker.domain.repository.TrackerRepository
import javax.inject.Inject

class DeleteTrackedFood @Inject constructor(private val repository: TrackerRepository) {

    suspend operator fun invoke(food: TrackedFood) {
        repository.deleteTrackedFood(food)
    }
}