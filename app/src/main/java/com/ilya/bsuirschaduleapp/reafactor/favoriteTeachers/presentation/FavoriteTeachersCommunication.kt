package com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.presentation

import com.ilya.bsuirschaduleapp.reafactor.core.Communication
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListItemUi

/**
 * Created by HP on 02.10.2022.
 **/
interface FavoriteTeachersCommunication: Communication.Mutable<List<TeacherListItemUi>> {

    class Base(defaultValue: List<TeacherListItemUi>):
        Communication.UiUpdate<List<TeacherListItemUi>>(defaultValue),
            FavoriteTeachersCommunication
}