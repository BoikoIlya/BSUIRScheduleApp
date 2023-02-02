package com.ilya.bsuirschaduleapp.reafactor.teacherList.data.cache

import com.ilya.bsuirschaduleapp.reafactor.core.Mapper
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.TeacherListItemDomain

/**
 * Created by HP on 24.10.2022.
 **/
interface TeacherCacheToTeacherDomain: Mapper<List<TeacherCache>, List<TeacherListItemDomain>> {

    class Base: TeacherCacheToTeacherDomain{
        override fun map(data: List<TeacherCache>): List<TeacherListItemDomain> {
            val result = emptyList<TeacherListItemDomain>().toMutableList()
            data.forEach { result.add(TeacherListItemDomain.Base(
                fio = it.fio,
                academicDepartment = it.academicDepartment,
                firstName = it.firstName,
                lastName = it.lastName,
                middleName = it.middleName,
                photoLink = it.photoLink,
                rank = it.rank,
                urlId = it.urlId
            )) }
            return result
        }

    }
}