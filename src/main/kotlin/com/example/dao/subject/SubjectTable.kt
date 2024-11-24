package com.example.dao.subject

import com.example.dao.group.GroupTable
import com.example.dao.group.GroupTable.autoIncrement
import com.example.dao.group.GroupTable.nullable
import com.example.dao.teacher.TeacherTable
import org.jetbrains.exposed.sql.Table


object SubjectTable: Table(name = "subjects") {
    val id = long(name = "subject_id").uniqueIndex()
    val name = varchar(name = "subject_name", length = 255)
    val dayOfWeek = integer(name = "subject_day_of_week")
    val startTime = varchar(name = "subject_start_time", length = 250)
    val endTime = varchar(name = "subject_end_time", length = 250)
    val classRoom = integer(name = "subject_class_room")
    val isLecture = bool(name = "subject_is_lecture").default(false)

    val teacherId = reference("teacher_id", TeacherTable.id)
    val groupId = reference("group_id", GroupTable.id)

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id)

}

data class SubjectRow(
    val id: Long,
    val name: String,
    val dayOfWeek: Int,
    val startTime: String,
    val endTime: String,
    val classRoom: Int,
    val isLecture: Boolean,
    val teacherId: Long,
    val groupId: Long
)
