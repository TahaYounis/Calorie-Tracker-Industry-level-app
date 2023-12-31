package com.example.tracker_data.mapper

import com.example.tracker_domain.model.TrackableFood
import com.plcoding.tracker_data.remote.dto.Product
import kotlin.math.roundToInt

// API return list of products take it and map it into trackableFood entity and that called mapper

fun Product.toTrackableFood(): TrackableFood?{
    val carbsPer100g = nutriments.carbohydrates100g.roundToInt()
    val proteinPer100g = nutriments.proteins100g.roundToInt()
    val fatPer100g = nutriments.proteins100g.roundToInt()
    val caloriesPer100g = nutriments.energyKcal100g.roundToInt()

    return TrackableFood(
        name = productName ?: return null,
        carbsPer100g = carbsPer100g,
        proteinPer100g = proteinPer100g,
        fatPer100g = fatPer100g,
        caloriesPer100g = caloriesPer100g,
        imageUrl = imageFrontThumbUrl
    )
}