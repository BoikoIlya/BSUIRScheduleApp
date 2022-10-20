package com.ilya.bsuirschaduleapp.reafactor.GroupList.presentation

import com.ilya.bsuirschaduleapp.reafactor.core.Communication
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListCommunication
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListItemUi

/**
 * Created by HP on 09.10.2022.
 **/
interface GroupListCommunication: Communication.Mutable<List<GroupListItemUi>> {

    class Base(defaultValue: List<GroupListItemUi>):
        GroupListCommunication,
        Communication.UiUpdate<List<GroupListItemUi>>(defaultValue)
}