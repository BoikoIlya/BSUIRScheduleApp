package com.ilya.bsuirschaduleapp.reafactor.teacherList.data.cache

import com.ilya.bsuirschaduleapp.reafactor.AbstractListDataSource
import com.ilya.bsuirschaduleapp.reafactor.core.ScheduleDao
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.TeacherListItemDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by HP on 23.09.2022.
 **/

    class BaseTeacherListCacheDataSource @Inject constructor(
        private val dBCache: ScheduleDao
        ): AbstractListDataSource<TeacherListItemDomain>(){

        override suspend fun find(query: String): Flow<List<TeacherListItemDomain>> = dBCache.getTeacherByFIO(query)

        override suspend fun saveItem(data: TeacherListItemDomain) = dBCache.insertTeacher(data.get())


}
