package com.ilya.bsuirschaduleapp.reafactor.teacherList.data.cache

import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.TeacherListItemDomain

/**
 * Created by HP on 24.10.2022.
 **/
interface TeacherDomainToTeacherCache: TeacherListItemDomain.Mapper<TeacherCache> {

    class Base:TeacherDomainToTeacherCache  {
        override fun map(
            fio: String,
            academicDepartment: String,
            firstName: String,
            lastName: String,
            middleName: String,
            photoLink: String,
            rank: String,
            urlId: String,
        ): TeacherCache {
            return TeacherCache(
                fio = fio,
                academicDepartment = academicDepartment,
                firstName = firstName,
                lastName = lastName,
                middleName = middleName,
                photoLink = photoLink,
                rank = rank,
                urlId = urlId,
                date = System.currentTimeMillis()
            )
        }
    }
}