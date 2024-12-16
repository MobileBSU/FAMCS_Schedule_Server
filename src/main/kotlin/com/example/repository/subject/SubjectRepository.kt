package com.example.repository.subject

import com.example.model.SubjectResponse
import com.example.util.Response

interface SubjectRepository {
    suspend fun getSubjectsByGroup(id: Long): Response<SubjectResponse>
    suspend fun getSubjectsByTeacher(id: Long): Response<SubjectResponse>
    suspend fun getSubjectById(id: Long): Response<SubjectResponse>
}