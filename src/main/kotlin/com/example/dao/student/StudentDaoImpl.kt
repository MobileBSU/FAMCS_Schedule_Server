package com.example.dao.student

import com.example.dao.DatabaseFactory.dbQuery
import com.example.model.SignUpParams
import com.example.model.Student
import com.example.model.StudentRow
import com.example.security.hashPassword
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class StudentDaoImpl : StudentDao {
    override suspend fun insert(params: SignUpParams): Student? {
        return dbQuery {
            val insertStatement = StudentRow.insert {
                it[name] = params.name
                it[email] = params.email
                it[password] = hashPassword(params.password)
            }

            insertStatement.resultedValues?.singleOrNull()?.let {
                rowToStudent(it)
            }
        }
    }

    override suspend fun findByEmail(email: String): Student? {
        return dbQuery {
            StudentRow.selectAll().where { StudentRow.email eq email }
                .map { rowToStudent(it) }
                .singleOrNull()
        }
    }

    private fun rowToStudent(row: ResultRow): Student {
        return Student(
            id = row[StudentRow.id],
            name = row[StudentRow.name],
            email = row[StudentRow.email],
            avatar = row[StudentRow.avatar],
            password = row[StudentRow.password]
        )
    }
}