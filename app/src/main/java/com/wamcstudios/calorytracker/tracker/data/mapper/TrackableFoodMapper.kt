package com.wamcstudios.calorytracker.tracker.data.mapper

import com.wamcstudios.calorytracker.tracker.data.remote.dto.Product
import com.wamcstudios.calorytracker.tracker.domain.model.TrackableFood
import kotlin.math.roundToInt

fun Product.toTrackableFood(): TrackableFood? {

    val carbsPer100g = nutriments.carbohydrates100g.roundToInt()
    val proteinPer100g = nutriments.proteins100g.roundToInt()
    val fatPer100g = nutriments.fat100g.roundToInt()
    val caloriesPer100g = nutriments.energyKcal100g.roundToInt()

    return TrackableFood(
        name = this.productName ?: return null,
        imageUrl = this.imageFrontThumbUrl,
        carbsPer100g = carbsPer100g,
        caloriesPer100g = caloriesPer100g,
        proteinPer100g = proteinPer100g,
        fatPer100g = fatPer100g
    )
}