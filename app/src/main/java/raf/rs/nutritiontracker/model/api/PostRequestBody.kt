package raf.rs.nutritiontracker.model.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostRequestBody(
    val userId: String,
    val title: String,
    val body: String
)