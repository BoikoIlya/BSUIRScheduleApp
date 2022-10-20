package com.ilya.bsuirschaduleapp.reafactor.GroupList.presentation

import com.ilya.bsuirschaduleapp.reafactor.core.Communication
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListProgressCommunication

/**
 * Created by HP on 09.10.2022.
 **/

interface GroupListProgressCommunication: Communication.Mutable<Boolean> {

    class Base: GroupListProgressCommunication, Communication.UiUpdate<Boolean>(false)
}