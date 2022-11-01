package com.ilya.bsuirschaduleapp.reafactor.teacherList.data

import com.ilya.bsuirschaduleapp.reafactor.core.*
import com.ilya.bsuirschaduleapp.reafactor.teacherList.data.cloud.ToTeacherListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.teacherList.data.teacherList.cloud.TeacherCloud
import com.ilya.bsuirschaduleapp.reafactor.teacherList.data.cloud.TeacherListCloudDataSource
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.TeacherListItemDomain

/**
 * Created by HP on 17.09.2022.
 **/
class BaseTeacherListRepository(
    private val cloudDataSource: TeacherListCloudDataSource,
    private val cacheDataSource: ListCacheDataSource<TeacherListItemDomain>,
    private val mapper: ToTeacherListItemDomain,
    private val favoriteCacheDataSource: FavoriteListCacheDataSource
):  AbstractRepository<TeacherCloud, TeacherListItemDomain>
    (mapper, cacheDataSource, favoriteCacheDataSource){

    override suspend fun cloudData(): List<TeacherCloud> = cloudDataSource.latestList()

    override suspend fun saveData(data: List<TeacherListItemDomain>) = cacheDataSource.save(data)



}

