package com.ilya.bsuirschaduleapp.data.repositories

import com.ilya.bsuirschaduleapp.data.network.api.ScheduleApi
import com.ilya.bsuirschaduleapp.data.network.dto.GroupDto
import com.ilya.bsuirschaduleapp.data.network.dto.ScheduleResponseDto
import com.ilya.bsuirschaduleapp.data.network.dto.TeacherDto
import com.ilya.bsuirschaduleapp.domain.repositiries.NetworkRepository
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val scheduleApi: ScheduleApi,
): NetworkRepository {

    override suspend fun getScheduleByGroupNumber(groupNumber: String): ScheduleResponseDto {
       return scheduleApi.getScheduleByGroupNumber(groupNumber)
    }

    override suspend fun getScheduleByTeacherUrlIdNumber(teacherUrlId: String): ScheduleResponseDto {
       return scheduleApi.getScheduleByTeacherUrlId(teacherUrlId)
    }

    override suspend fun getCurrentWeekFromApi(): Int {
        return scheduleApi.getCurrentWeek()
    }

    override suspend fun getGroupsFromApi(): List<GroupDto> {
       return scheduleApi.getListOfGroups()
    }

    override suspend fun getTeachersFromApi(): List<TeacherDto> {
       return scheduleApi.getListOfTeachers()
    }



}