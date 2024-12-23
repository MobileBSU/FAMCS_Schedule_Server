package com.scheduleAppKtor.dao.student

import com.scheduleAppKtor.dao.group.GroupTable
import org.jetbrains.exposed.sql.Table

object StudentTable: Table(name = "students") {
    val id = long(name = "student_id").uniqueIndex()
    val name = varchar(name = "student_name", length = 250)
    val email = varchar(name = "student_email", length = 250)
    val password = varchar(name = "student_password", length = 100)
    val imageUrl = text(name = "student_image_url").nullable()

    val groupId = reference("group_id", GroupTable.id).nullable()

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id)

}

data class StudentRow(
    val id: Long,
    val name: String,
    val email: String,
    val imageUrl: String?,
    val password: String,
    val groupId: Long? = null
)