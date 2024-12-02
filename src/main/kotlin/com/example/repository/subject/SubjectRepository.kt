package com.example.repository.subject

import com.example.model.SubjectParam
import com.example.model.SubjectResponse
import com.example.util.Response

interface SubjectRepository {
    suspend fun getSubjectsByGroup(param: SubjectParam): Response<SubjectResponse>
    suspend fun getSubjectsByTeacher(param: SubjectParam): Response<SubjectResponse>
}