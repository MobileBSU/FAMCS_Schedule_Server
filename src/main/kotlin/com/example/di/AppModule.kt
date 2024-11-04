package com.example.di

import com.example.dao.student.StudentDao
import com.example.dao.student.StudentDaoImpl
import com.example.model.StudentRow
import com.example.repository.student.StudentRepository
import com.example.repository.student.StudentRepositoryImpl
import org.koin.dsl.module

val appModule = module {
    single<StudentRepository> { StudentRepositoryImpl(get ()) }
    single<StudentDao> { StudentDaoImpl() }
}