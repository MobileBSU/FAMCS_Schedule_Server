package com.example.dao.student

import com.example.dao.DatabaseFactory.dbQuery
import com.example.model.SignUpParams
import com.example.security.hashPassword
import com.example.util.IdGenerator
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class StudentDaoImpl : StudentDao {
    override suspend fun insert(params: SignUpParams): StudentRow? {
        return dbQuery {
            val insertStatement = StudentTable.insert {
                it[id] = IdGenerator.generateId()
                it[name] = params.name
                it[email] = params.email
                it[password] = hashPassword(params.password)
            }

            insertStatement.resultedValues?.singleOrNull()?.let {
                rowToStudent(it)
            }
        }
    }

    override suspend fun findByEmail(email: String): StudentRow? {
        return dbQuery {
            StudentTable.selectAll().where { StudentTable.email eq email }
                .map { rowToStudent(it) }
                .singleOrNull()
        }
    }

    private fun rowToStudent(row: ResultRow): StudentRow {
        return StudentRow(
            id = row[StudentTable.id],
            name = row[StudentTable.name],
            email = row[StudentTable.email],
            imageUrl = row[StudentTable.imageUrl],
            password = row[StudentTable.password],
            groupId = row[StudentTable.groupId]
        )
    }
}