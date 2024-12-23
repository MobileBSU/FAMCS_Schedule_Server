package com.scheduleAppKtor.di

import com.scheduleAppKtor.dao.group.GroupDao
import com.scheduleAppKtor.dao.group.GroupDaoImpl
import com.scheduleAppKtor.dao.student.StudentDao
import com.scheduleAppKtor.dao.student.StudentDaoImpl
import com.scheduleAppKtor.dao.subject.SubjectDao
import com.scheduleAppKtor.dao.subject.SubjectDaoImpl
import com.scheduleAppKtor.dao.teacher.TeacherDao
import com.scheduleAppKtor.dao.teacher.TeacherDaoImpl
import com.scheduleAppKtor.repository.auth.AuthRepository
import com.scheduleAppKtor.repository.auth.AuthRepositoryImpl
import com.scheduleAppKtor.repository.group.GroupRepository
import com.scheduleAppKtor.repository.group.GroupRepositoryImpl
import com.scheduleAppKtor.repository.student.StudentRepository
import com.scheduleAppKtor.repository.student.StudentRepositoryImpl
import com.scheduleAppKtor.repository.subject.SubjectRepository
import com.scheduleAppKtor.repository.subject.SubjectRepositoryImpl
import com.scheduleAppKtor.repository.teacher.TeacherRepository
import com.scheduleAppKtor.repository.teacher.TeacherRepositoryImpl
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