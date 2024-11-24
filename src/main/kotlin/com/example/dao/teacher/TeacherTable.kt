package com.example.dao.teacher

import com.example.dao.student.StudentTable.autoIncrement
import com.example.dao.student.StudentTable.nullable
import org.jetbrains.exposed.sql.Table


object TeacherTable: Table(name = "teachers") {
    val id = long(name = "teacher_id").uniqueIndex()
    val name = varchar(name = "teacher_name", length = 250)
    val bio = text(name = "teacher_bio")
    val imageUrl = text(name = "teacher_image_url").nullable()

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id)

}

data class TeacherRow(
    val id: Long,
    val name: String,
    val bio: String,
    val imageUrl: String?,
)