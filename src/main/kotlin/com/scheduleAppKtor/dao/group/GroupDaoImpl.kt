package com.scheduleAppKtor.dao.group

import com.scheduleAppKtor.dao.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.CharColumnType
import org.jetbrains.exposed.sql.Expression
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.castTo
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.selectAll

class GroupDaoImpl : GroupDao {
    override suspend fun getAllGroups(): List<GroupRow> {
        return dbQuery{
            GroupTable.selectAll().map{ rowToGroup(it) }
        }
    }

    override suspend fun getGroupsByName(input: String): List<GroupRow> {


        return dbQuery {
            GroupTable.selectAll()
                .where { (GroupTable.name like "%$input%") or
                        (GroupTable.groupNumber.castToString() like "%$input%") }
                .map {rowToGroup(it)}
        }
    }

    override suspend fun getGroupById(id: Long): GroupRow {
        return dbQuery {
            GroupTable
                .selectAll()
                .where{GroupTable.id eq id}
                .map { rowToGroup(it) }
                .single()
        }
    }

    override suspend fun getGroupByCourse(course: Int, group: Int): GroupRow {
        return dbQuery {
            GroupTable
                .selectAll()
                .where{(GroupTable.course eq course) and
                        (GroupTable.groupNumber eq group)}
                .map { rowToGroup(it) }
                .single()
        }
    }

    private fun rowToGroup(row: ResultRow): GroupRow {
        return GroupRow(
            id = row[GroupTable.id],
            course = row[GroupTable.course],
            groupNumber = row[GroupTable.groupNumber],
            name = row[GroupTable.name],
            subGroupNumber = row[GroupTable.subGroupNumber]
        )
    }

}
fun Expression<Int>.castToString() = castTo(CharColumnType())