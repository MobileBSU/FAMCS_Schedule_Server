package com.example.repository.student

import com.example.dao.student.StudentDao
import com.example.model.StudentResponse
import com.example.model.StudentResponseData
import com.example.model.UpdateStudentParams
import com.example.util.Response
import io.ktor.http.HttpStatusCode

class StudentRepositoryImpl(
    private val studentDao: StudentDao
): StudentRepository {
    override suspend fun update(id: Long, params: UpdateStudentParams): Response<StudentResponse> {
        val student = studentDao.update(id, params)

        return if(student == null) {
            Response.Error(
                code = HttpStatusCode.BadRequest,
                data = StudentResponse(
                    errorMessage = "Something went wrong"
                )
            )
        } else {
            Response.Success(
                data = StudentResponse(
                    data = StudentResponseData(
                        id = student.id,
                        name = student.name,
                        email = student.email,
                        groupId = student.groupId,
                        imageUrl = student.imageUrl,
                        password = student.password
                    )
                )
            )
        }
    }

    override suspend fun getById(id: Long): Response<StudentResponse> {
        val student = studentDao.findById(id)

        return if(student == null) {
            Response.Error(
                code = HttpStatusCode.NotFound,
                data = StudentResponse(
                    errorMessage = "Something gone wrong"
                )
            )
        } else {
            Response.Success(
                data = StudentResponse(
                    data = StudentResponseData(
                        id = student.id,
                        name = student.name,
                        email = student.email,
                        groupId = student.groupId,
                        imageUrl = student.imageUrl,
                        password = student.password
                    )
                )
            )
        }
    }
}