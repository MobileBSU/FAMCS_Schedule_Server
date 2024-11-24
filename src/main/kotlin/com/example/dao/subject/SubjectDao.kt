package com.example.dao.subject

interface SubjectDao {
    suspend fun getSubjects() : List<SubjectRow>
}