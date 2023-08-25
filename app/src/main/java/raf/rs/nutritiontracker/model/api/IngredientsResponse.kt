package raf.rs.nutritiontracker.model.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IngredientsResponse(
    val meals: List<IngredientResponse>
)