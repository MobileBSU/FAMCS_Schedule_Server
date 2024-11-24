package com.example.dao.student

import com.example.model.SignUpParams

interface StudentDao {
    suspend fun insert(params: SignUpParams): StudentRow?
    suspend fun findByEmail(email: String): StudentRow?
}