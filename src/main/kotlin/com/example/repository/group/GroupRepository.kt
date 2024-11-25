package com.example.repository.group

import com.example.dao.group.GroupRow
import com.example.model.GroupResponse
import com.example.model.GroupSearchParam
import com.example.util.Response

interface GroupRepository {
    suspend fun getAllGroups(): Response<GroupResponse>
    suspend fun getGroupsByName(param: GroupSearchParam): Response<GroupResponse>
}