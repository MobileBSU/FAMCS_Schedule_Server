package com.example.repository.group

import com.example.dao.group.GroupDao
import com.example.model.GroupResponse
import com.example.model.GroupResponseData
import com.example.model.GroupSearchParam
import com.example.util.Response
import io.ktor.http.HttpStatusCode

class GroupRepositoryImpl(
    private val groupDao: GroupDao
) : GroupRepository {
    override suspend fun getAllGroups(): Response<GroupResponse> {
        return try {
            val groups = groupDao.getAllGroups()

            val groupResponseData = groups.map { groupRow ->
                groupRow.let {
                    GroupResponseData(
                        id = groupRow.id,
                        course = groupRow.course,
                        groupNumber = groupRow.groupNumber,
                        name = groupRow.name,
                        subGroupNumber = groupRow.subGroupNumber
                    )
                }
            }

            Response.Success(data = GroupResponse(data = groupResponseData))
        } catch (e: Exception) {
            Response.Error(
                code = HttpStatusCode.Forbidden,
                data = GroupResponse(
                    errorMessage = e.toString()
                )
            )

        }
    }

    override suspend fun getGroupsByName(param: GroupSearchParam): Response<GroupResponse> {
        return try {
            if (param.input.isNotBlank()) {
                val groups = groupDao.getGroupsByName(param.input)

                if (groups.isEmpty()) {
                    Response.Error(
                        code = HttpStatusCode.NotFound,
                        data = GroupResponse(
                            errorMessage = "No such groups"
                        )
                    )
                } else {
                    val groupResponseData = groups.map { groupRow ->
                        GroupResponseData(
                            id = groupRow.id,
                            course = groupRow.course,
                            groupNumber = groupRow.groupNumber,
                            name = groupRow.name,
                            subGroupNumber = groupRow.subGroupNumber
                        )
                    }

                    Response.Success(data = GroupResponse(data = groupResponseData))
                }
            } else {
                Response.Error(
                    code = HttpStatusCode.BadRequest,
                    data = GroupResponse(
                        errorMessage = "Input cannot be empty"
                    )
                )
            }
        } catch (e: Exception) {
            Response.Error(
                code = HttpStatusCode.InternalServerError,
                data = GroupResponse(
                    errorMessage = e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        }
    }

    override suspend fun getGroupById(id: Long): Response<GroupResponse> {
        return try {
            val group = groupDao.getGroupById(id)

            val groupResponseData = group.let {groupRow ->
                GroupResponseData(
                    id = groupRow.id,
                    course = groupRow.course,
                    groupNumber = groupRow.groupNumber,
                    name = groupRow.name,
                    subGroupNumber = groupRow.subGroupNumber
                )
            }
            Response.Success(data = GroupResponse(data = listOf(groupResponseData)))

        } catch (e: Exception) {
            Response.Error(
                code = HttpStatusCode.Forbidden,
                data = GroupResponse(
                    errorMessage = e.toString()
            )
        )
        }
    }
}