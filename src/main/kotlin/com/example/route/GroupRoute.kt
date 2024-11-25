package com.example.route

import com.example.model.AuthResponse
import com.example.model.GroupResponse
import com.example.model.GroupSearchParam
import com.example.repository.group.GroupRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receiveNullable
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

    route(path = "/groupByName") {
        get {
            try {
                val param = call.receiveNullable<GroupSearchParam>()

                if (param == null) {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = GroupResponse(
                            errorMessage = "Invalid params"
                        )
                    )

                    return@get
                }
                val result = repository.getGroupsByName(param)

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
}