package com.example.plugins

import com.example.repository.auth.AuthRepository
import com.example.repository.group.GroupRepository
import com.example.repository.student.StudentRepository
import com.example.repository.subject.SubjectRepository
import com.example.repository.teacher.TeacherRepository
import com.example.route.authRouting
import com.example.route.groupRouting
import com.example.route.studentRouting
import com.example.route.subjectRouting
import com.example.route.teacherRouting
import io.ktor.server.application.*
import io.ktor.server.plugins.swagger.swaggerUI
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val authRepository by inject<AuthRepository>()
    val subjectRepository by inject<SubjectRepository>()
    val groupRepository by inject<GroupRepository>()
    val teacherRepository by inject<TeacherRepository>()
    val studentRepository by inject<StudentRepository>()
    routing {

        swaggerUI(path = "swagger", swaggerFile = "swagger/documentation.yaml") {
            version = "4.15.1"
        }

        authRouting(authRepository)
        subjectRouting(subjectRepository)
        groupRouting(groupRepository)
        teacherRouting(teacherRepository)
        studentRouting(studentRepository)
    }
}
