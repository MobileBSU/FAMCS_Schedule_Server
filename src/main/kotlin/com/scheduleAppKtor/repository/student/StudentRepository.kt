package com.scheduleAppKtor.repository.student

import com.scheduleAppKtor.model.StudentResponse
import com.scheduleAppKtor.model.UpdateStudentParams
import com.scheduleAppKtor.util.Response

interface StudentRepository {
    suspend fun update(id: Long, params: UpdateStudentParams): Response<StudentResponse>
    suspend fun getById(id: Long) : Response<StudentResponse>
}