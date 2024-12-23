package com.scheduleAppKtor.model
import kotlinx.serialization.Serializable

@Serializable
data class UpdateStudentParams(
    val name: String? = null,
    val email: String? = null,
    val password: String? = null,
    val imageUrl: String? = null,
    val groupId: Long? = null
)

@Serializable
data class StudentResponse(
    val data: StudentResponseData? = null,
    val errorMessage: String? = null
)

@Serializable
data class StudentResponseData(
    val id: Long,
    val name: String,
    val email: String,
    val imageUrl: String?,
    val password: String,
    val groupId: Long? = null
)