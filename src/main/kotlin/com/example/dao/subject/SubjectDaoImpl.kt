package com.example.dao.subject

import com.example.dao.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class SubjectDaoImpl : SubjectDao {
    override suspend fun getSubjects(): List<SubjectRow> {
        return dbQuery {
            SubjectTable.selectAll().map { rowToSubject(it) }
        }
    }

    private fun rowToSubject(row: ResultRow): SubjectRow {
        return SubjectRow(
            id = row[SubjectTable.id],
            name = row[SubjectTable.name],
            dayOfWeek = row[SubjectTable.dayOfWeek],
            startTime = row[SubjectTable.startTime],
            endTime = row[SubjectTable.endTime],
            classRoom = row[SubjectTable.classRoom],
            isLecture = row[SubjectTable.isLecture],
            teacherId = row[SubjectTable.teacherId],
            groupId = row[SubjectTable.groupId]
        )
    }
}