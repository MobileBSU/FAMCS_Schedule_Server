package com.example.repository.teacher

import com.example.model.TeacherResponse
import com.example.model.TeacherSearchParam
import com.example.util.Response

interface TeacherRepository {
    suspend fun getTeachersByName(param: TeacherSearchParam): Response<TeacherResponse>
}