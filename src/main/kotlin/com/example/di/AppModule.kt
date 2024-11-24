package com.example.di

import com.example.dao.student.StudentDao
import com.example.dao.student.StudentDaoImpl
import com.example.dao.subject.SubjectDao
import com.example.dao.subject.SubjectDaoImpl
import com.example.repository.auth.StudentRepository
import com.example.repository.auth.StudentRepositoryImpl
import com.example.repository.subject.SubjectRepository
import com.example.repository.subject.SubjectRepositoryImpl
import org.koin.dsl.module

val appModule = module {
    single<StudentRepository> { StudentRepositoryImpl(get ()) }
    single<StudentDao> { StudentDaoImpl() }

    single<SubjectRepository> { SubjectRepositoryImpl(get()) }
    single<SubjectDao> { SubjectDaoImpl() }
}