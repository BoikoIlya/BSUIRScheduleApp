package com.ilya.bsuirschaduleapp.reafactor.groupList.data.cache

import com.ilya.bsuirschaduleapp.reafactor.core.AbstractListCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.groupList.domain.GroupListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.core.ListCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.core.ScheduleDao
import javax.inject.Inject

/**
 * Created by HP on 08.10.2022.
 **/

interface GroupListCacheDataSource: ListCacheDataSource<GroupListItemDomain> {

    class Base @Inject constructor(
        private val dBCache: ScheduleDao,
        toCacheMapper: ToGroupCacheMapper,
        private val fromCacheMapper: GroupCacheToGroupDomain
    ) : AbstractListCacheDataSource<GroupListItemDomain, GroupCache>(toCacheMapper), GroupListCacheDataSource {

        override suspend fun find(query: String): List<GroupListItemDomain> =
           fromCacheMapper.map(dBCache.getGroupByName(query))

        override suspend fun saveItem(data: GroupCache) = dBCache.insertGroup(data)

    }
}