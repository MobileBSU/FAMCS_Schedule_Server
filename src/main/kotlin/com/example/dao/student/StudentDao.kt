package com.example.dao.student

import com.example.model.SignUpParams
import com.example.model.UpdateStudentParams

interface StudentDao {
    suspend fun insert(params: SignUpParams): StudentRow?
    suspend fun findByEmail(email: String): StudentRow?
    suspend fun update(id: Long, params: UpdateStudentParams): StudentRow?
    suspend fun findById(id: Long): StudentRow?
}