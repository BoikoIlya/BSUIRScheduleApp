package com.ilya.bsuirschaduleapp.reafactor.teacherList.data.cloud

import com.ilya.bsuirschaduleapp.reafactor.core.Mapper
import com.ilya.bsuirschaduleapp.reafactor.teacherList.data.teacherList.cloud.TeacherCloud
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.TeacherListItemDomain

/**
 * Created by HP on 17.09.2022.
 **/
interface ToTeacherListItemDomain: Mapper<List<TeacherCloud>, List<TeacherListItemDomain>> {

    class Base(private val mapper: TeacherCloud.Mapper<TeacherListItemDomain>):ToTeacherListItemDomain{

        override fun map(data: List<TeacherCloud>): List<TeacherListItemDomain> {
            return data.map { it.map(mapper) }
        }

    }
}
