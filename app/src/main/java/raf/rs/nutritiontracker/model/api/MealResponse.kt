package raf.rs.nutritiontracker.model.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MealResponse(
    val strMeal: String? = null,
    val strMealThumb: String? = null,
    val idMeal: Int? = null,
)