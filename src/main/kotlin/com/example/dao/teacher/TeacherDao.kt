package com.example.dao.teacher

interface TeacherDao {
    suspend fun getAllTeachers(): List<TeacherRow>
    suspend fun getTeacherByName(input: String): List<TeacherRow>
}