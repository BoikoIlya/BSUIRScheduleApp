package com.ilya.bsuirschaduleapp.reafactor.schadule.presentation

import com.ilya.bsuirschaduleapp.reafactor.core.Communication

/**
 * Created by HP on 25.10.2022.
 **/
interface ScheduleProgressCommunication: Communication.Mutable<Boolean> {

    class Base: Communication.UiUpdate<Boolean>(false), ScheduleProgressCommunication
}