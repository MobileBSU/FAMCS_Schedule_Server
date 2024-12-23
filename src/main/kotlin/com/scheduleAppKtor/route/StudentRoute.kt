package com.scheduleAppKtor.route

import com.scheduleAppKtor.model.UpdateStudentParams
import com.scheduleAppKtor.repository.student.StudentRepository
import com.scheduleAppKtor.util.Response
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.patch
import io.ktor.server.routing.route

fun Routing.studentRouting(
    repository: StudentRepository
) {
    route(path = "/students/{id}") {
        patch {
            val id = call.parameters["id"]?.toLongOrNull()
            if (id == null) {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = "Invalid student ID"
                )
                return@patch
            }

            val params = call.receive<UpdateStudentParams>()
            when (
                val response = repository.update(id, params)
            ) {
                is Response.Success -> call.respond(HttpStatusCode.OK, response.data)
                is Response.Error -> call.respond(response.code, response.data.errorMessage ?: "Error occurred")
            }
        }

        get {
            val id = call.parameters["id"]?.toLongOrNull()
            if (id == null) {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = "Invalid student ID"
                )
                return@get
            }

            when(
                val response = repository.getById(id)
            ) {
                is Response.Success -> call.respond(HttpStatusCode.OK, response.data)
                is Response.Error -> call.respond(response.code, response.data.errorMessage  ?: "Error occurred")
            }
        }

    }
}