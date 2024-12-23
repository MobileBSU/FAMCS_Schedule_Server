package com.scheduleAppKtor.route

import com.scheduleAppKtor.model.GroupResponse
import com.scheduleAppKtor.repository.group.GroupRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Routing.groupRouting(
    repository: GroupRepository
) {
    route(path = "/groups") {
        get {
            try {
                val result = repository.getAllGroups()

                if (result.data.errorMessage != null) {
                    call.respond(
                        status = HttpStatusCode.NotFound,
                        message = result.data.errorMessage
                    )
                } else {
                    call.respond(
                        status = HttpStatusCode.OK,
                        message = result.data
                    )
                }
            } catch(e: Exception) {
                call.respond(
                    status = HttpStatusCode.InternalServerError,
                    message = e.localizedMessage ?: "An unknown error occurred"
                )
            }
        }
    }

    route(path = "/groupByName/{name}") {
        get {
            try {
                val input = call.parameters["name"]

                if (input == null) {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = GroupResponse(
                            errorMessage = "Invalid params"
                        )
                    )

                    return@get
                }
                val result = repository.getGroupsByName(input)

                call.respond(
                    status = result.code,
                    message = result.data
                )

            }catch (e: Exception) {
                call.respond(
                    status = HttpStatusCode.InternalServerError,
                    message = e.localizedMessage ?: "An unknown error occurred"
                )
            }
        }
    }

    route(path = "/group/{id}") {
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

                val result = repository.getGroupById(id)

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

    route(path = "/group/{course}/{group}") {
        get {
            try {


                val course = call.parameters["course"]?.toIntOrNull()
                val group = call.parameters["group"]?.toIntOrNull()

                if (course == null || group == null) {
                    call.respond(
                        status = HttpStatusCode.NotFound,
                        message = "Invalid group ID"
                    )

                    return@get
                }

                val result = repository.getGroupByCourse(course, group)
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