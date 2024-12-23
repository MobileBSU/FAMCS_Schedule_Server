package com.scheduleAppKtor.repository.group

import com.scheduleAppKtor.model.GroupResponse
import com.scheduleAppKtor.util.Response

interface GroupRepository {
    suspend fun getAllGroups(): Response<GroupResponse>
    suspend fun getGroupsByName(input: String): Response<GroupResponse>
    suspend fun getGroupById(id: Long): Response<GroupResponse>
}