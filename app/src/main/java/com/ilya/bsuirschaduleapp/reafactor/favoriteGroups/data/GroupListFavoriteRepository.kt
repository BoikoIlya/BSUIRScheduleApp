package com.ilya.bsuirschaduleapp.reafactor.favoriteGroups.data

import com.ilya.bsuirschaduleapp.reafactor.groupList.domain.GroupListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.groupList.presentation.GroupListItemUi
import com.ilya.bsuirschaduleapp.reafactor.core.ListCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.data.FavoriteRepository


/**
 * Created by HP on 09.10.2022.
 **/

interface GroupListFavoriteRepository: FavoriteRepository<GroupListItemUi> {

    class Base(
        cacheDataSource: ListCacheDataSource<GroupListItemDomain>,
        mapper: ToFavoriteGroupListUi
    ) : FavoriteRepository.Abstract<GroupListItemDomain, GroupListItemUi>
        (cacheDataSource,mapper), GroupListFavoriteRepository
}