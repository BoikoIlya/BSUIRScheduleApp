package com.ilya.bsuirschaduleapp.reafactor.schadule.presentation

import com.ilya.bsuirschaduleapp.reafactor.core.Communication

/**
 * Created by HP on 25.10.2022.
 **/
interface ScheduleCommunication: Communication.Mutable<ScheduleUi> {

    class Base: Communication.UiUpdate<ScheduleUi>(
        ScheduleUi(name = "",
        schedules = listOf(),
        exams = listOf())
    ), ScheduleCommunication
}