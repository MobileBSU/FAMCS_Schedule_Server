package com.example.di

import com.example.dao.group.GroupDao
import com.example.dao.group.GroupDaoImpl
import com.example.dao.student.StudentDao
import com.example.dao.student.StudentDaoImpl
import com.example.dao.subject.SubjectDao
import com.example.dao.subject.SubjectDaoImpl
import com.example.dao.teacher.TeacherDao
import com.example.dao.teacher.TeacherDaoImpl
import com.example.repository.auth.AuthRepository
import com.example.repository.auth.AuthRepositoryImpl
import com.example.repository.group.GroupRepository
import com.example.repository.group.GroupRepositoryImpl
import com.example.repository.student.StudentRepository
import com.example.repository.student.StudentRepositoryImpl
import com.example.repository.subject.SubjectRepository
import com.example.repository.subject.SubjectRepositoryImpl
import com.example.repository.teacher.TeacherRepository
import com.example.repository.teacher.TeacherRepositoryImpl
import org.koin.dsl.module

val appModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get ()) }
    single<StudentDao> { StudentDaoImpl() }

    single<SubjectRepository> { SubjectRepositoryImpl(get()) }
    single<SubjectDao> { SubjectDaoImpl() }

    single<GroupRepository> {GroupRepositoryImpl(get())}
    single<GroupDao> {GroupDaoImpl()}

    single<TeacherRepository>{TeacherRepositoryImpl(get()) }
    single<TeacherDao> { TeacherDaoImpl() }

    single<StudentRepository> { StudentRepositoryImpl(get()) }
    single<StudentDao> { StudentDaoImpl() }
}