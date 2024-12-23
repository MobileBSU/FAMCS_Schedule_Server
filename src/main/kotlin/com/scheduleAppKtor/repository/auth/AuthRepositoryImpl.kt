package com.scheduleAppKtor.repository.auth

import com.scheduleAppKtor.dao.student.StudentDao
import com.scheduleAppKtor.model.AuthResponse
import com.scheduleAppKtor.model.AuthResponseData
import com.scheduleAppKtor.model.SignInParams
import com.scheduleAppKtor.model.SignUpParams
import com.scheduleAppKtor.plugins.generateToke
import com.scheduleAppKtor.security.hashPassword
import com.scheduleAppKtor.util.Response
import io.ktor.http.HttpStatusCode

class AuthRepositoryImpl(
    private val studentDao: StudentDao
) : AuthRepository {
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
                            imageUrl = insertedStudent.imageUrl,
                            token = generateToke(params.email),
                            email = insertedStudent.email
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
                            token = generateToke(params.email),
                            email = student.email
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