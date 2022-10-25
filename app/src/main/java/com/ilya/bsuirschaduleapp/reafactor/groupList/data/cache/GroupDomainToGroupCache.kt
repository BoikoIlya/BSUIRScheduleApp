package com.ilya.bsuirschaduleapp.reafactor.groupList.data.cache

import com.ilya.bsuirschaduleapp.reafactor.groupList.domain.GroupListItemDomain

/**
 * Created by HP on 24.10.2022.
 **/

interface GroupDomainToGroupCache: GroupListItemDomain.Mapper<GroupCache>{

class Base: GroupDomainToGroupCache {
    override fun map(
        course: Int,
        facultyName: String,
        name: String,
        specialityName: String,
    ): GroupCache {
        return GroupCache(
            course = course,
            facultyName = facultyName,
            name = name,
            specialityName = specialityName,
            date = System.currentTimeMillis()
        )
    }
}
}