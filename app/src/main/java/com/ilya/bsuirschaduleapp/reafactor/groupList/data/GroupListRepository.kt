package com.ilya.bsuirschaduleapp.reafactor.groupList.data

import com.ilya.bsuirschaduleapp.reafactor.groupList.data.cloud.GroupCloud
import com.ilya.bsuirschaduleapp.reafactor.groupList.data.cloud.GroupCloudDataSource
import com.ilya.bsuirschaduleapp.reafactor.groupList.data.cloud.ToGroupListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.groupList.domain.GroupListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.core.AbstractRepository
import com.ilya.bsuirschaduleapp.reafactor.core.FavoriteListCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.core.ListCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.core.Repository

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