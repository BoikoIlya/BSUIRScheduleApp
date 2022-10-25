package com.ilya.bsuirschaduleapp.reafactor.teacherList.data.cache

import com.ilya.bsuirschaduleapp.reafactor.core.Mapper
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.TeacherListItemDomain

/**
 * Created by HP on 24.10.2022.
 **/
interface ToTeacherCacheMapper: Mapper<List<TeacherListItemDomain>, List<TeacherCache>> {

    class Base(private val mapper: TeacherListItemDomain.Mapper<TeacherCache>): ToTeacherCacheMapper{
        override fun map(data: List<TeacherListItemDomain>): List<TeacherCache> {
          return data.map { it.map(mapper) }
        }

    }
}

