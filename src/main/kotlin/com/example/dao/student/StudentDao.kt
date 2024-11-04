package com.example.dao.student

import com.example.model.SignUpParams
import com.example.model.Student

interface StudentDao {
    suspend fun insert(params: SignUpParams): Student?
    suspend fun findByEmail(email: String): Student?
}