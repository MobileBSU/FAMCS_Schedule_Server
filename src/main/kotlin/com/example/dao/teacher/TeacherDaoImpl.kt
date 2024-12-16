package com.example.dao.teacher

import com.example.dao.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.selectAll

class TeacherDaoImpl: TeacherDao {
    override suspend fun getAllTeachers(): List<TeacherRow> {
        return dbQuery {
            TeacherTable
                .selectAll()
                .map { rowToTeacher(it) }
        }
    }

    override suspend fun getTeacherByName(input: String): List<TeacherRow> {
        return dbQuery{
            TeacherTable
                .selectAll()
                .where {TeacherTable.name like "%$input%"}
                .map { rowToTeacher(it) }
        }
    }

    override suspend fun getTeacherById(id: Long): TeacherRow {
        return dbQuery {
            TeacherTable
                .selectAll()
                .where(TeacherTable.id eq id)
                .map { rowToTeacher(it) }
                .single()
        }
    }

    private fun rowToTeacher(row: ResultRow) : TeacherRow {
        return TeacherRow(
            id = row[TeacherTable.id],
            name = row[TeacherTable.name],
            bio = row[TeacherTable.bio],
            imageUrl = row[TeacherTable.imageUrl]
        )
    }
}