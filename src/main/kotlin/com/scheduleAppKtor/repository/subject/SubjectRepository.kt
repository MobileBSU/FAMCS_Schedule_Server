package com.scheduleAppKtor.repository.subject

import com.scheduleAppKtor.model.SubjectResponse
import com.scheduleAppKtor.util.Response

interface SubjectRepository {
    suspend fun getSubjectsByGroup(id: Long): Response<SubjectResponse>
    suspend fun getSubjectsByTeacher(id: Long): Response<SubjectResponse>
    suspend fun getSubjectById(id: Long): Response<SubjectResponse>
}