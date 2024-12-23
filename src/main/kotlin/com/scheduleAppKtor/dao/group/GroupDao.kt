package com.scheduleAppKtor.dao.group

interface GroupDao {
    suspend fun getAllGroups(): List<GroupRow>
    suspend fun getGroupsByName(input: String): List<GroupRow>
    suspend fun getGroupById(id: Long): GroupRow
    suspend fun getGroupByCourse(course: Int, group: Int): GroupRow
}