package com.ilya.bsuirschaduleapp.data.network.dto

import com.ilya.bsuirschaduleapp.domain.models.ScheduleResponse

data class ScheduleResponseDto (
    val employeeDto: TeacherDto? = null,
    val studentGroupDto: StudentGroupDto? =null,
    val schedules: Schedules,
    val exams: List<Schedule>,
    val startDate: String,
    val endDate: String,
    val startExamsDate: String? =null,
    val endExamsDate: String?=null
)

fun ScheduleResponseDto.toScheduleResponse(): ScheduleResponse {
    return ScheduleResponse(
        schedules = schedules,
        exams = exams,
        startDate = startDate,
        endDate = endDate,
        startExamsDate = startExamsDate.toString(),
        endExamsDate = endExamsDate.toString(),
        studentGroupDto = studentGroupDto,
        employeeDto = employeeDto,
    )
}
