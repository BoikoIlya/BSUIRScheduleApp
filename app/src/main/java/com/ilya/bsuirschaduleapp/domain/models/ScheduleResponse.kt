package com.ilya.bsuirschaduleapp.domain.models

import com.ilya.bsuirschaduleapp.data.network.dto.*
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cloud.ScheduleCloud

data class ScheduleResponse(
    val schedules: ScheduleCloud.Schedules,
    val exams: List<ScheduleCloud.Schedule>,
    val startExamsDate: String,
    val endExamsDate: String,
    val studentGroupDto: StudentGroupDto? =null,
    val employeeDto: TeacherDto? = null,
)
