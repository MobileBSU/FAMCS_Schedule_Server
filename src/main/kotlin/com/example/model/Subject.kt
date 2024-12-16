package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class SubjectResponse(
    val data: List<SubjectResponseData>? = null,
    val errorMessage: String? = null
)

@Serializable
data class SubjectResponseData (
    val id: Long,
    val name: String,
    val dayOfWeek: Int,
    val startTime: String,
    val endTime: String,
    val classRoom: Int,
    val isLecture: Boolean,
    val teacherId: Long,
    val groupId: Long
)