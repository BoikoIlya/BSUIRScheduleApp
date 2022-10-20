package com.ilya.bsuirschaduleapp.reafactor.GroupList.data.cache

import com.ilya.bsuirschaduleapp.reafactor.AbstractListDataSource
import com.ilya.bsuirschaduleapp.reafactor.GroupList.domain.GroupListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.core.ListCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.core.ScheduleDao
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.TeacherListItemDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by HP on 08.10.2022.
 **/

interface GroupListCacheDataSource: ListCacheDataSource<GroupListItemDomain> {

    class Base @Inject constructor(
        private val dBCache: ScheduleDao
    ) : AbstractListDataSource<GroupListItemDomain>(), GroupListCacheDataSource {

        override suspend fun find(query: String): Flow<List<GroupListItemDomain>> =
            dBCache.getGroupByName(query)

        override suspend fun saveItem(data: GroupListItemDomain) = dBCache.insertGroup(data.get())
    }
}