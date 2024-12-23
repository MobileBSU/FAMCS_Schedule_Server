package com.scheduleAppKtor.dao.teacher

interface TeacherDao {
    suspend fun getAllTeachers(): List<TeacherRow>
    suspend fun getTeacherByName(input: String): List<TeacherRow>
    suspend fun getTeacherById(id: Long): TeacherRow
}