package com.wamcstudios.calorytracker.tracker.data.mapper

import com.wamcstudios.calorytracker.tracker.data.local.entity.TrackedFoodEntity
import com.wamcstudios.calorytracker.tracker.domain.model.MealType
import com.wamcstudios.calorytracker.tracker.domain.model.TrackableFood
import com.wamcstudios.calorytracker.tracker.domain.model.TrackedFood
import java.time.LocalDate

fun TrackedFoodEntity.toTrackedFood(): TrackedFood {
    return TrackedFood(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        carbs = this.carbs,
        protein = this.protein,
        fat = this.fat,
        mealType = MealType.fromString(type),
        amount = this.amount,
        date = LocalDate.of(this.year, this.month, this.dayOfMonth),
        calories = this.calories
    )
}

fun TrackedFood.toTrackerFoodEntity(): TrackedFoodEntity {
    return TrackedFoodEntity(
        id = this.id,
        name = this.name,
        carbs = this.carbs,
        protein = this.protein,
        fat = this.fat,
        calories = this.calories,
        imageUrl = this.imageUrl,
        type = this.mealType.name,
        amount = this.amount,
        dayOfMonth = this.date.dayOfMonth,
        month = this.date.monthValue,
        year = this.date.year
    )
}