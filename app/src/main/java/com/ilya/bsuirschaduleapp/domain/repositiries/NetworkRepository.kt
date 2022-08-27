package com.ilya.bsuirschaduleapp.domain.repositiries

import com.ilya.bsuirschaduleapp.data.network.dto.GroupDto
import com.ilya.bsuirschaduleapp.data.network.dto.ScheduleResponseDto
import com.ilya.bsuirschaduleapp.data.network.dto.TeacherDto
import com.ilya.bsuirschaduleapp.domain.models.Group
import com.ilya.bsuirschaduleapp.domain.models.SelectedGroup
import com.ilya.bsuirschaduleapp.domain.models.SelectedTeacher
import com.ilya.bsuirschaduleapp.domain.models.Teacher
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface NetworkRepository {

    suspend fun getScheduleByGroupNumber(groupNumber: String): ScheduleResponseDto

    suspend fun getScheduleByTeacherUrlIdNumber(teacherUrlId: String): ScheduleResponseDto

    suspend fun getCurrentWeekFromApi():Int

    suspend fun getGroupsFromApi(): List<GroupDto>

    suspend fun getTeachersFromApi(): List<TeacherDto>

}