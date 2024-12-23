package com.scheduleAppKtor.plugins

import com.scheduleAppKtor.repository.auth.AuthRepository
import com.scheduleAppKtor.repository.group.GroupRepository
import com.scheduleAppKtor.repository.student.StudentRepository
import com.scheduleAppKtor.repository.subject.SubjectRepository
import com.scheduleAppKtor.repository.teacher.TeacherRepository
import com.scheduleAppKtor.route.authRouting
import com.scheduleAppKtor.route.groupRouting
import com.scheduleAppKtor.route.studentRouting
import com.scheduleAppKtor.route.subjectRouting
import com.scheduleAppKtor.route.teacherRouting
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
