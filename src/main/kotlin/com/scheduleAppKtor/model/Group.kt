package com.scheduleAppKtor.model

import kotlinx.serialization.Serializable

@Serializable
data class GroupSearchParam(
    val input: String
)

@Serializable
data class GroupResponse(
    val data: List<GroupResponseData>? = null,
    val errorMessage: String? = null
)

@Serializable
data class GroupResponseData (
    val id: Long,
    val course: Int,
    val groupNumber: Int,
    val name: String,
    val subGroupNumber: Int?,
)