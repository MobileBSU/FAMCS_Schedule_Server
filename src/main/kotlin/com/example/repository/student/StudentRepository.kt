package com.example.repository.student

import com.example.model.StudentResponse
import com.example.model.UpdateStudentParams
import com.example.util.Response

interface StudentRepository {
    suspend fun update(id: Long, params: UpdateStudentParams): Response<StudentResponse>
    suspend fun getById(id: Long) : Response<StudentResponse>
}