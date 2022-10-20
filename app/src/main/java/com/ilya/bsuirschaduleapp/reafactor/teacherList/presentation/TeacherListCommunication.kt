package com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation

import com.ilya.bsuirschaduleapp.reafactor.core.Communication

/**
 * Created by HP on 17.09.2022.
 **/
interface TeacherListCommunication: Communication.Mutable<List<TeacherListItemUi>> {

    class Base(defaultValue: List<TeacherListItemUi>):
        TeacherListCommunication,
        Communication.UiUpdate<List<TeacherListItemUi>>(defaultValue)
}