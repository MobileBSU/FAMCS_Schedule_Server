package com.example.route

import com.example.repository.subject.SubjectRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Routing.subjectRouting(repository: SubjectRepository) {
    route(path = "/subject/group/{id}") {
        get {
            try {
                val id = call.parameters["id"]?.toLongOrNull()
                if (id == null) {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = "Invalid group ID"
                    )

                    return@get
                }

                val result = repository.getSubjectsByGroup(id)


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

            } catch (e: Exception) {
                call.respond(
                    status = HttpStatusCode.InternalServerError,
                    message = e.localizedMessage ?: "An unknown error occurred"
                )
            }
        }
    }

    route(path = "/subject/teacher/{id}") {
        get {
            try {
                val id = call.parameters["id"]?.toLongOrNull()
                if (id == null) {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = "Invalid teacher ID"
                    )

                    return@get
                }

                val result = repository.getSubjectsByTeacher(id)


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
            } catch (e: Exception) {
                call.respond(
                    status = HttpStatusCode.InternalServerError,
                    message = e.localizedMessage ?: "An unknown error occurred"
                )
            }
        }
    }

    route(path = "/subject/{id}") {
        get {
            try {
                val id = call.parameters["id"]?.toLongOrNull()
                if (id == null) {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = "Invalid teacher ID"
                    )

                    return@get
                }

                val result = repository.getSubjectById(id)

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
            } catch (e: Exception) {
                call.respond(
                    status = HttpStatusCode.InternalServerError,
                    message = e.localizedMessage ?: "An unknown error occurred"
                )
            }
        }
    }
}