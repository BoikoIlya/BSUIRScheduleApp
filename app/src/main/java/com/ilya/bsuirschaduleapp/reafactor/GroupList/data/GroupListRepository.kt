package com.ilya.bsuirschaduleapp.reafactor.GroupList.data

import com.ilya.bsuirschaduleapp.reafactor.GroupList.data.cloud.GroupCloud
import com.ilya.bsuirschaduleapp.reafactor.GroupList.data.cloud.GroupCloudDataSource
import com.ilya.bsuirschaduleapp.reafactor.GroupList.data.cloud.ToGroupListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.GroupList.domain.GroupListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.core.AbstractRepository
import com.ilya.bsuirschaduleapp.reafactor.core.FavoriteListCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.core.ListCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.core.Repository
import com.ilya.bsuirschaduleapp.reafactor.teacherList.data.cloud.TeacherListCloudDataSource
import com.ilya.bsuirschaduleapp.reafactor.teacherList.data.cloud.ToTeacherListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.teacherList.data.teacherList.cloud.TeacherCloud
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.TeacherListItemDomain

/**
 * Created by HP on 08.10.2022.
 **/

interface GroupListRepository: Repository<List<GroupListItemDomain>> {

    class Base(
        private val cloudDataSource: GroupCloudDataSource,
        private val cacheDataSource: ListCacheDataSource<GroupListItemDomain>,
        private val mapper: ToGroupListItemDomain,
        private val favoriteCacheDataSource: FavoriteListCacheDataSource
    ) : AbstractRepository<GroupCloud, GroupListItemDomain>
        (mapper, cacheDataSource, favoriteCacheDataSource), GroupListRepository {

        override suspend fun cloudData(query: String): List<GroupCloud> =
            cloudDataSource.latestList()

        override suspend fun saveData(data: List<GroupListItemDomain>) = cacheDataSource.save(data)
    }
}