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
    override suspend fun getAllTeachers(): Response<TeacherResponse> {
        return try {
            val teachers = teacherDao.getAllTeachers()

            val teacherResponseData = teachers.map { teacherRow ->
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

    override suspend fun getTeachersByName(input: String): Response<TeacherResponse> {
        return try {
            val teachers = teacherDao.getTeacherByName(input)

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

    override suspend fun getTeacherById(id: Long): Response<TeacherResponse> {
        return try {
            val teacher = teacherDao.getTeacherById(id)

            val teacherResponseData = teacher.let {
                    TeacherResponseData(
                        id = it.id,
                        name = it.name,
                        bio = it.bio,
                        imageUrl = it.imageUrl
                    )
                }

            Response.Success(data = TeacherResponse(data = listOf(teacherResponseData)))
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