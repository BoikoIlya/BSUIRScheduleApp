package com.ilya.bsuirschaduleapp.reafactor.groupList.presentation

import com.ilya.bsuirschaduleapp.reafactor.groupList.domain.GroupListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.core.Mapper

interface ToGroupListItemUi: Mapper<List<GroupListItemDomain>, List<GroupListItemUi>> {

    class Base(val mapper: GroupListItemDomain.Mapper<GroupListItemUi>): ToGroupListItemUi{
        override fun map(data: List<GroupListItemDomain>): List<GroupListItemUi> =
            data.map { it.map(mapper) }
    }
}