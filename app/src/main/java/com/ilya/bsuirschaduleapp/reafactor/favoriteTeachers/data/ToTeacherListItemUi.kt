package com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.data

import com.ilya.bsuirschaduleapp.reafactor.core.Mapper
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.TeacherListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListItemUi

/**
 * Created by HP on 07.10.2022.
 **/
interface ToTeacherListItemUi: Mapper<List<TeacherListItemDomain>, List<TeacherListItemUi>> {

    class Base(val mapper: TeacherListItemDomain.Mapper<TeacherListItemUi>): ToTeacherListItemUi{
        override fun map(data: List<TeacherListItemDomain>): List<TeacherListItemUi> =
            data.map { it.map(mapper) }
    }
}