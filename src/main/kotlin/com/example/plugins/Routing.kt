package com.example.plugins

import com.example.repository.student.StudentRepository
import com.example.route.authRouting
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val studentRepository by inject<StudentRepository>()
    routing {
        authRouting(studentRepository)
    }
}
