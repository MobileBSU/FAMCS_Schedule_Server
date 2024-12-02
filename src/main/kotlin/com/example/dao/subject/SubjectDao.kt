package com.example.dao.subject

interface SubjectDao {
    suspend fun getSubjectsByGroup(id: Long) : List<SubjectRow>
    suspend fun getSubjectsByTeacher(id: Long): List<SubjectRow>
}