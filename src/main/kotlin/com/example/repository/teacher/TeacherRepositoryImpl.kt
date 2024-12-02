package com.example.repository.teacher

import com.example.dao.teacher.TeacherDao
import com.example.model.TeacherResponse
import com.example.model.TeacherResponseData
import com.example.model.TeacherSearchParam
import com.example.util.Response
import io.ktor.http.HttpStatusCode

class TeacherRepositoryImpl(
    private val teacherDao: TeacherDao
) : TeacherRepository {
    override suspend fun getTeachersByName(param: TeacherSearchParam): Response<TeacherResponse> {
        return try {
            val teachers = teacherDao.getTeacherByName(param.input)

            val teacherResponseData = teachers.map {teacherRow ->
                teacherRow.let {
                    TeacherResponseData(
                        id = it.id,
                        name = it.name,
                        bio = it.bio,
                        imageUrl = it.imageUrl
                    )
                }
            }

            Response.Success(data = TeacherResponse(data = teacherResponseData))
        } catch (e: Exception) {
            Response.Error(
                code = HttpStatusCode.NotFound,
                data = TeacherResponse(
                    errorMessage = "Ooooops, something gone wrong"
                )
            )
        }
    }
}