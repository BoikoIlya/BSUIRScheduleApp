package com.ilya.bsuirschaduleapp.presentation.models


import com.ilya.bsuirschaduleapp.domain.models.ScheduleResponse
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cloud.ScheduleCloud

data class ScheduleState(
    val data: ScheduleResponse= ScheduleResponse(schedules = ScheduleCloud.Schedules(Monday = listOf(),
        Tuesday = listOf(),
        Wednesday = listOf(),
        Thursday = listOf(),
        Friday = listOf(),
        Saturday = listOf()),
        exams = listOf(),
        startExamsDate = "",
        endExamsDate = "",
        studentGroupDto = null,
        employeeDto = null),
    val error: String = "",
    val isLoading: Boolean = true
)