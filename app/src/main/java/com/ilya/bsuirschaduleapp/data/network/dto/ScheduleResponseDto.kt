package com.ilya.bsuirschaduleapp.data.network.dto

import com.ilya.bsuirschaduleapp.domain.models.ScheduleResponse
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cloud.ScheduleCloud

data class ScheduleResponseDto (
    val employeeDto: TeacherDto? = null,
    val studentGroupDto: StudentGroupDto? =null,
    val schedules: ScheduleCloud.Schedules,
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
        startExamsDate = startExamsDate.toString(),
        endExamsDate = endExamsDate.toString(),
        studentGroupDto = studentGroupDto,
        employeeDto = employeeDto,
    )
}
