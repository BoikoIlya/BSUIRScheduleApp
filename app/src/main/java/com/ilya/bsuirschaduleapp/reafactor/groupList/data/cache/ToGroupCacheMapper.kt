package com.ilya.bsuirschaduleapp.reafactor.groupList.data.cache

import com.ilya.bsuirschaduleapp.reafactor.groupList.domain.GroupListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.core.Mapper

/**
 * Created by HP on 24.10.2022.
 **/
interface ToGroupCacheMapper: Mapper<List<GroupListItemDomain>, List<GroupCache>> {

    class Base(private val mapper: GroupListItemDomain.Mapper<GroupCache>): ToGroupCacheMapper{
        override fun map(data: List<GroupListItemDomain>): List<GroupCache> {
           return data.map { it.map(mapper) }
        }

    }
}

