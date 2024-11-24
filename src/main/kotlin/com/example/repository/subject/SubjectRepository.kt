package com.example.repository.subject

import com.example.model.SubjectResponse
import com.example.util.Response

interface SubjectRepository {
    suspend fun getSubjects(): Response<SubjectResponse>
}