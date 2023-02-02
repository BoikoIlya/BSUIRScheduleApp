package com.ilya.bsuirschaduleapp.reafactor.teacherList.data.cache

import com.ilya.bsuirschaduleapp.reafactor.core.AbstractListCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.core.ScheduleDao
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.TeacherListItemDomain
import javax.inject.Inject

/**
 * Created by HP on 23.09.2022.
 **/

    class BaseTeacherListCacheDataSource @Inject constructor(
        private val dBCache: ScheduleDao,
        toCacheMapper: ToTeacherCacheMapper,
        private val fromCacheMapper: TeacherCacheToTeacherDomain
        ): AbstractListCacheDataSource<TeacherListItemDomain, TeacherCache>(toCacheMapper){

        override suspend fun find(query: String): List<TeacherListItemDomain> =
           fromCacheMapper.map(dBCache.getTeacherByFIO(query))

        override suspend fun saveItem(data: TeacherCache) = dBCache.insertTeacher(data)


}
