package com.example.model

import org.jetbrains.exposed.sql.Table

object StudentRow: Table(name = "students") {
    val id = integer(name = "student_id").autoIncrement()
    val name = varchar(name = "student_name", length = 250)
    val email = varchar(name = "student_email", length = 250)
    val password = varchar(name = "student_password", length = 100)
    val avatar = text(name = "student_avatar").nullable()

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id)

}

data class Student(
    val id: Int,
    val name: String,
    val email: String,
    val avatar: String?,
    val password: String
)