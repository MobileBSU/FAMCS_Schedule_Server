package com.scheduleAppKtor.route

import com.scheduleAppKtor.model.AuthResponse
import com.scheduleAppKtor.model.SignInParams
import com.scheduleAppKtor.model.SignUpParams
import com.scheduleAppKtor.repository.auth.AuthRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Routing.authRouting(repository: AuthRepository) {

    route(path = "/signup") {
        post {
            val params = call.receiveNullable<SignUpParams>()

            if(params == null) {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = AuthResponse(
                        errorMessage = "Invalid credentials!"
                    )
                )

                return@post
            }

            val result = repository.signUp(params = params)

            call.respond(
                status = result.code,
                message = result.data
            )
        }
    }

    route(path = "/login") {
        post {
            val params = call.receiveNullable<SignInParams>()

            if(params == null) {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = AuthResponse(
                        errorMessage = "Invalid credentials!"
                    )
                )

                return@post
            }

            val result = repository.signIn(params = params)
            call.respond(
                status = result.code,
                message = result.data
            )
        }
    }
}