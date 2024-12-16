package com.example.repository.subject

import com.example.dao.subject.SubjectDao
import com.example.model.SubjectResponse
import com.example.model.SubjectResponseData
import com.example.util.Response
import io.ktor.http.HttpStatusCode

class SubjectRepositoryImpl(
    private val subjectDao: SubjectDao
) : SubjectRepository {
    override suspend fun getSubjectsByGroup(id: Long): Response<SubjectResponse> {

        return try {
            val lessons = subjectDao.getSubjectsByGroup(id)

            val lessonResponseData = lessons.map { lessonRow ->
                lessonRow.let {
                    SubjectResponseData(
                        id = it.id,
                        name = it.name,
                        dayOfWeek = it.dayOfWeek,
                        startTime = it.startTime,
                        endTime = it.endTime,
                        classRoom = it.classRoom,
                        isLecture = it.isLecture,
                        teacherId = it.teacherId,
                        groupId = it.groupId
                    )
                }
            }

            Response.Success(data = SubjectResponse(data = lessonResponseData)) // Пример: только первый элемент
        } catch (e: Exception) {
            Response.Error(
                code = HttpStatusCode.NotFound,
                data = SubjectResponse(
                    errorMessage = "Ooops, something happend"
                )
            )
        }
    }

    override suspend fun getSubjectsByTeacher(id: Long): Response<SubjectResponse> {
        return try {
            val lessons = subjectDao.getSubjectsByTeacher(id)

            val lessonResponseData = lessons.map { lessonRow ->
                lessonRow.let {
                    SubjectResponseData(
                        id = it.id,
                        name = it.name,
                        dayOfWeek = it.dayOfWeek,
                        startTime = it.startTime,
                        endTime = it.endTime,
                        classRoom = it.classRoom,
                        isLecture = it.isLecture,
                        teacherId = it.teacherId,
                        groupId = it.groupId
                    )
                }
            }

            Response.Success(data = SubjectResponse(data = lessonResponseData)) // Пример: только первый элемент
        } catch (e: Exception) {
            Response.Error(
                code = HttpStatusCode.NotFound,
                data = SubjectResponse(
                    errorMessage = "Ooops, something happend"
                )
            )
        }
    }

    override suspend fun getSubjectById(id: Long): Response<SubjectResponse> {
        return try {
            val subject = subjectDao.getSubjectById(id)

            val subjectResponseData = subject.let {
                SubjectResponseData(
                    id = it.id,
                    name = it.name,
                    dayOfWeek = it.dayOfWeek,
                    startTime = it.startTime,
                    endTime = it.endTime,
                    classRoom = it.classRoom,
                    isLecture = it.isLecture,
                    teacherId = it.teacherId,
                    groupId = it.groupId
                )
            }

            Response.Success(data = SubjectResponse(data = listOf(subjectResponseData)))
        } catch (e: Exception) {
            Response.Error(
                code = HttpStatusCode.NotFound,
                data = SubjectResponse(
                    errorMessage = "Ooops, something happend"
                )
            )
        }
    }
}