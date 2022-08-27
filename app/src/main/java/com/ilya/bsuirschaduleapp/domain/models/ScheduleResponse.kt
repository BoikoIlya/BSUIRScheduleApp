package com.ilya.bsuirschaduleapp.domain.models

import com.ilya.bsuirschaduleapp.data.network.dto.*

data class ScheduleResponse(
    val schedules: Schedules,
    val exams: List<Schedule>,
    val startExamsDate: String,
    val endExamsDate: String,
    val studentGroupDto: StudentGroupDto? =null,
    val employeeDto: TeacherDto? = null,
    val startDate: String,
    val endDate: String,
)
