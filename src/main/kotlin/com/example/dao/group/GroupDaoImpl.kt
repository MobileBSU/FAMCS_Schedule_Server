package com.example.dao.group

import com.example.dao.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like
import org.jetbrains.exposed.sql.select
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
                .where { GroupTable.name like input }
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