package com.example.dao.group

interface GroupDao {
    suspend fun getAllGroups(): List<GroupRow>
    suspend fun getGroupsByName(input: String): List<GroupRow>
}