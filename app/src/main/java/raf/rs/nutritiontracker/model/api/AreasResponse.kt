package raf.rs.nutritiontracker.model.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AreasResponse(
    val meals: List<AreaResponse>
)