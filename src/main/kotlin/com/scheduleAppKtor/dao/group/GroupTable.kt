package com.scheduleAppKtor.dao.group

import org.jetbrains.exposed.sql.Table


object GroupTable: Table(name = "groups") {
    val id = long(name = "group_id").uniqueIndex()
    val course = integer(name = "group_course")
    val groupNumber = integer(name = "group_number")
    val name = varchar(name = "group_name", length = 250)
    val subGroupNumber = integer(name = "group_sub_number").nullable()


    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id)

}

data class GroupRow(
    val id: Long,
    val course: Int,
    val groupNumber: Int,
    val name: String,
    val subGroupNumber: Int?,
)