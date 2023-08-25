package raf.rs.nutritiontracker.model.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostPutRequestBody(
    val userId: String,
    val id: String,
    val title: String,
    val body: String
)