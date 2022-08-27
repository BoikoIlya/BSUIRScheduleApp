package com.ilya.bsuirschaduleapp.data.network.api

import com.ilya.bsuirschaduleapp.data.network.dto.GroupDto
import com.ilya.bsuirschaduleapp.data.network.dto.ScheduleResponseDto
import com.ilya.bsuirschaduleapp.data.network.dto.StudentGroup
import com.ilya.bsuirschaduleapp.data.network.dto.TeacherDto
import com.ilya.bsuirschaduleapp.utils.Constance
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ScheduleApi {

    @GET(Constance.GROUP_SCHEDULE_DESTINATION_URL)
    suspend fun getScheduleByGroupNumber(
        @Query("studentGroup")
        studentGroup: String
    ): ScheduleResponseDto

    @GET(Constance.TEACHER_SCHEDULE_DESTINATION_URL)
    suspend fun getScheduleByTeacherUrlId(
        @Path("urlId")
        urlId: String
    ): ScheduleResponseDto

    @GET(Constance.CURRENT_WEEK_DESTINATION_URL)
    suspend fun getCurrentWeek():Int

    @GET(Constance.GROUP_LIST_DESTINATION_URL)
    suspend fun getListOfGroups(): List<GroupDto>

    @GET(Constance.TEACHER_LIST_DESTINATION_URL)
    suspend fun getListOfTeachers(): List<TeacherDto>
}