package com.example.route

import com.example.model.TeacherSearchParam
import com.example.repository.teacher.TeacherRepository
import com.example.util.Response
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Routing.teacherRouting(repository: TeacherRepository) {
    route(path = "/teachers") {
        get {
            try {
                val result = repository.getAllTeachers()

                when (result) {
                    is Response.Error -> call.respond(
                        status = result.code,
                        message = result.data.errorMessage ?: "Error occurred"
                    )

                    is Response.Success -> call.respond(status = result.code, message = result.data)
                }
            } catch (e: Exception) {
                call.respond(
                    status = HttpStatusCode.InternalServerError,
                    message = e.localizedMessage ?: "An unknown error occurred"
                )
            }
        }
            route(path = "/{name}") {
                get {
                    try {
                        val name = call.parameters["name"].toString()
                        if (name.isNotEmpty()) {
                            val result = repository.getTeachersByName(name)

                            if (result.data.errorMessage != null) {
                                call.respond(
                                    status = HttpStatusCode.InternalServerError,
                                    message = result.data.errorMessage
                                )
                            } else {
                                call.respond(
                                    status = HttpStatusCode.OK,
                                    message = result.data
                                )
                            }

                        }
                    } catch (e: Exception) {
                        call.respond(
                            status = HttpStatusCode.InternalServerError,
                            message = e.localizedMessage ?: "An unknown error occurred"
                        )
                    }
                }
            }
        }
    }
