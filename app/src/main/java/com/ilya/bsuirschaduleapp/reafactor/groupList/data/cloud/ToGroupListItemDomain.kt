package com.ilya.bsuirschaduleapp.reafactor.groupList.data.cloud

import com.ilya.bsuirschaduleapp.reafactor.groupList.domain.GroupListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.core.Mapper


/**
 * Created by HP on 08.10.2022.
 **/

interface ToGroupListItemDomain: Mapper<List<GroupCloud>, List<GroupListItemDomain>> {

    class Base(private val mapper: GroupCloud.Mapper<GroupListItemDomain>):
        ToGroupListItemDomain {

        override fun map(data: List<GroupCloud>): List<GroupListItemDomain> {
            return data.map { it.map(mapper) }
        }

    }
}