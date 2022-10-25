package com.ilya.bsuirschaduleapp.reafactor.favoriteGroups.data

import com.ilya.bsuirschaduleapp.reafactor.groupList.domain.GroupListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.groupList.presentation.GroupListItemUi
import com.ilya.bsuirschaduleapp.reafactor.core.Mapper

/**
 * Created by HP on 09.10.2022.
 **/

interface ToFavoriteGroupListUi: Mapper<List<GroupListItemDomain>, List<GroupListItemUi>> {

    class Base(val mapper: GroupListItemDomain.Mapper<GroupListItemUi>):
        ToFavoriteGroupListUi {
        override fun map(data: List<GroupListItemDomain>): List<GroupListItemUi> =
            data.map { it.map(mapper) }.filter { it.isFavorite }
    }
}