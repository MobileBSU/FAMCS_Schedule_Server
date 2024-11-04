package com.example.repository.student

import com.example.dao.student.StudentDao
import com.example.model.AuthResponse
import com.example.model.AuthResponseData
import com.example.model.SignInParams
import com.example.model.SignUpParams
import com.example.plugins.generateToke
import com.example.security.hashPassword
import com.example.util.Response
import io.ktor.http.HttpStatusCode

class StudentRepositoryImpl(
    private val studentDao: StudentDao
) : StudentRepository {
    override suspend fun signUp(params: SignUpParams): Response<AuthResponse> {
        return if(studentAlreadyExist(params.email)) {
            Response.Error(
                code = HttpStatusCode.Conflict,
                data = AuthResponse(
                    errorMessage = "A student with this email already exists!"
                )
            )
        } else {
            val insertedStudent = studentDao.insert(params)

            if (insertedStudent == null) {
                Response.Error(
                    code = HttpStatusCode.InternalServerError,
                    data = AuthResponse(
                        errorMessage = "Oooops, sorry we could not register the user, try later!"
                    )
                )
            } else {
                Response.Success(
                    data = AuthResponse(
                        data = AuthResponseData(
                            id = insertedStudent.id,
                            name = insertedStudent.name,
                            avatar = insertedStudent.avatar,
                            token = generateToke(params.email)
                        )
                    )
                )
            }
        }
    }

    override suspend fun signIn(params: SignInParams): Response<AuthResponse> {
        val student = studentDao.findByEmail(params.email)

        return if (student == null) {
            Response.Error(
                code = HttpStatusCode.NotFound,
                data = AuthResponse(
                    errorMessage = "Invalid credentials, no user with this email!"
                )
            )
        }else {
            val hashedPassword = hashPassword(params.password)

            if (student.password == hashedPassword) {
                Response.Success(
                    data = AuthResponse(
                        data = AuthResponseData(
                            id = student.id,
                            name = student.name,
                            token = generateToke(params.email)
                        )
                    )
                )
            } else {
                Response.Error(
                    code = HttpStatusCode.Forbidden,
                    data = AuthResponse(
                        errorMessage = "Invalid credentials, wrong password"
                    )
                )
            }
        }
    }

    private suspend fun studentAlreadyExist(email: String): Boolean {
        return studentDao.findByEmail(email) != null
    }
}