package com.scheduleAppKtor.repository.teacher

import com.scheduleAppKtor.model.TeacherResponse
import com.scheduleAppKtor.util.Response

interface TeacherRepository {
    suspend fun getAllTeachers(): Response<TeacherResponse>
    suspend fun getTeachersByName(input: String): Response<TeacherResponse>
    suspend fun getTeacherById(id: Long): Response<TeacherResponse>
}