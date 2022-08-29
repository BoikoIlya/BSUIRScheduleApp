package com.ilya.bsuirschaduleapp.presentation.models

import com.ilya.bsuirschaduleapp.data.network.dto.Schedules
import com.ilya.bsuirschaduleapp.domain.models.ScheduleResponse

data class ScheduleState(
    val data: ScheduleResponse= ScheduleResponse(schedules = Schedules(Monday = listOf(),
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