package raf.rs.nutritiontracker.model.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IngredientResponse(
    val idIngredient: Int? = null,
    val strIngredient: String? = null,
    val strDescription: String? = null,
    val strType: String? = null
)