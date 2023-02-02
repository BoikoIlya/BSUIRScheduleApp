package com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation

import com.ilya.bsuirschaduleapp.reafactor.core.Communication

/**
 * Created by HP on 22.09.2022.
 **/
interface TeacherListProgressCommunication: Communication.Mutable<Boolean> {

    class Base:TeacherListProgressCommunication, Communication.UiUpdate<Boolean>(false)
}