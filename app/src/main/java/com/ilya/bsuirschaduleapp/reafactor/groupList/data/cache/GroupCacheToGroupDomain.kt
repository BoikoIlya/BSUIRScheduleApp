package com.ilya.bsuirschaduleapp.reafactor.groupList.data.cache

import com.ilya.bsuirschaduleapp.reafactor.groupList.domain.GroupListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.core.Mapper

/**
 * Created by HP on 24.10.2022.
 **/
interface GroupCacheToGroupDomain: Mapper<List<GroupCache>, List<GroupListItemDomain>> {

    class Base: GroupCacheToGroupDomain{
        override fun map(data: List<GroupCache>): List<GroupListItemDomain> {
            val result = emptyList<GroupListItemDomain>().toMutableList()
            data.forEach { result.add(GroupListItemDomain.Base(
                course = it.course,
                facultyName = it.facultyName,
                name = it.name,
                specialityName = it.specialityName)) }
            return result
        }

    }
}