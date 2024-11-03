package com.example.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

fun Application.configureSecurity() {
    // Please read the jwt property from the config file if you are using EngineMain
//    val jwtAudience = "jwt-audience"
    //    val jwtDomain = "https://jwt-provider-domain/"
//    val jwtRealm = "ktor sample app"
//    val jwtSecret = "secret"
    val jwtAudience = System.getenv("jwt.audience")
    val jwtDomain = System.getenv("jwt.domain")
    val jwtSecret = System.getenv("jwt.secret")
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
                if (credential.payload.audience.contains(jwtAudience)) JWTPrincipal(credential.payload) else null
            }
        }
    }
}
