package com.example.repository.subject

import com.example.dao.subject.SubjectDao
import com.example.model.SubjectParam
import com.example.model.SubjectResponse
import com.example.model.SubjectResponseData
import com.example.util.Response
import io.ktor.http.HttpStatusCode

class SubjectRepositoryImpl(
    private val subjectDao: SubjectDao
) : SubjectRepository {
    override suspend fun getSubjectsByGroup(param: SubjectParam): Response<SubjectResponse> {

        return try {
            val lessons = subjectDao.getSubjectsByGroup(param.id)

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

    override suspend fun getSubjectsByTeacher(param: SubjectParam): Response<SubjectResponse> {
        return try {
            val lessons = subjectDao.getSubjectsByTeacher(param.id)

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
}