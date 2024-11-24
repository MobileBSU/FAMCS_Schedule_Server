package com.example.dao

import com.example.dao.group.GroupTable
import com.example.dao.student.StudentTable
import com.example.dao.subject.SubjectTable
import com.example.dao.teacher.TeacherTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        Database.connect(createHikariDataSource())
        transaction {
            SchemaUtils.create(StudentTable, SubjectTable, GroupTable, TeacherTable)
        }
    }

    private fun createHikariDataSource(): HikariDataSource {
        val driverClass = "org.postgresql.Driver"
        val jdbcUrl = "jdbc:postgresql://localhost:5432/testdb"
        val username = "postgres"
        val password = "1234"

        val hikariConfig = HikariConfig().apply {
            driverClassName = driverClass
            setJdbcUrl(jdbcUrl)
            this.username = username
            this.password = password
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        }

        return HikariDataSource(hikariConfig)
    }

    suspend fun<T> dbQuery(block: suspend () -> T) =
        newSuspendedTransaction(Dispatchers.IO) {block()}
}