package com.scheduleAppKtor.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.scheduleAppKtor.model.AuthResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

private val jwtAudience = System.getenv("jwt.audience")
private val jwtDomain = System.getenv("jwt.domain")
private val jwtSecret = System.getenv("jwt.secret")

private const val CLAIM = "email"

fun Application.configureSecurity() {

    authentication {
        jwt {
            verifier(
                JWT
                    .require(Algorithm.HMAC256(jwtSecret))
                    .withAudience(jwtAudience)
                    .withIssuer(jwtDomain)
                    .build()
            )
            validate { credential ->
                if(credential.payload.getClaim(CLAIM).asString() != null){
                    JWTPrincipal(payload = credential.payload)
                } else {
                    null
                }
            }

            challenge { _,_ ->
                call.respond(
                    status = HttpStatusCode.Unauthorized,
                    message =  AuthResponse(
                        errorMessage = "Token is not valid or expired"
                    )
                )
            }
        }
    }
}

fun generateToke(email: String): String {
    return JWT.create()
        .withAudience(jwtAudience)
        .withIssuer(jwtDomain)
        .withClaim(CLAIM, email)
        //.withExpiresAt()
        .sign(Algorithm.HMAC256(jwtSecret))
}