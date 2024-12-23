package com.scheduleAppKtor.dao.student

import com.scheduleAppKtor.model.SignUpParams
import com.scheduleAppKtor.model.UpdateStudentParams

interface StudentDao {
    suspend fun insert(params: SignUpParams): StudentRow?
    suspend fun findByEmail(email: String): StudentRow?
    suspend fun update(id: Long, params: UpdateStudentParams): StudentRow?
    suspend fun findById(id: Long): StudentRow?
}