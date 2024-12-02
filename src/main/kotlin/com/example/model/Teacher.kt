package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class TeacherSearchParam(
    val input: String
)

@Serializable
data class TeacherResponse(
    val data: List<TeacherResponseData>? = null,
    val errorMessage: String? = null
)

@Serializable
data class TeacherResponseData (
    val id: Long,
    val name: String,
    val bio: String,
    val imageUrl: String?
)