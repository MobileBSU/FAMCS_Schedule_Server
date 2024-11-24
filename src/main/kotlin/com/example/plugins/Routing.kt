package com.example.plugins

import com.example.repository.auth.StudentRepository
import com.example.repository.subject.SubjectRepository
import com.example.route.authRouting
import com.example.route.subjectRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val studentRepository by inject<StudentRepository>()
    val subjectRepository by inject<SubjectRepository>()
    routing {
        authRouting(studentRepository)
        subjectRouting(subjectRepository)
    }
}
