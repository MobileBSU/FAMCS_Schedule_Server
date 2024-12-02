package com.example.dao.teacher

interface TeacherDao {
    suspend fun getTeacherByName(input: String): List<TeacherRow>
}