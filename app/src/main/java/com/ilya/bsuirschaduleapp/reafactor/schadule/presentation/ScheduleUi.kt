package com.ilya.bsuirschaduleapp.reafactor.schadule.presentation

import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cloud.ScheduleCloud
import com.ilya.bsuirschaduleapp.reafactor.schadule.domain.ScheduleDomain

/**
 * Created by HP on 25.10.2022.
 **/
data class ScheduleUi(
    val name: String,
    val schedules: List<List<ScheduleDomain.Schedule>>,
    val exams: List<ScheduleDomain.Schedule>
)
