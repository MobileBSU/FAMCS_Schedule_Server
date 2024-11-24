package com.example.route

import com.example.repository.subject.SubjectRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Routing.subjectRouting(repository: SubjectRepository) {
    route(path = "/subjects") {
        get {
            try {
                val result = repository.getSubjects()

                if (result.data.errorMessage != null) {
                    call.respond(
                        status = HttpStatusCode.InternalServerError,
                        message = result.data.errorMessage
                    )
                } else{
                    call.respond(
                        status = HttpStatusCode.OK,
                        message = result.data)
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