package com.wamcstudios.calorytracker.tracker.domain.use_case

data class TrackerUseCases(
    val trackFood: TrackFood,
    val searchFood: SearchFood,
    val getFoodsForDate: GetFoodsForDate,
    val deleteTrackedFood: DeleteTrackedFood, val calculateMealNutrients: CalculateMealNutrients,
)
