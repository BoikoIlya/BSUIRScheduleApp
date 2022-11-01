package com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.data

import com.ilya.bsuirschaduleapp.reafactor.core.ListCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.core.Mapper
import kotlinx.coroutines.flow.first

/**
 * Created by HP on 02.10.2022.
 **/
interface FavoriteRepository<T> {

    suspend fun favoritesList(): List<T>

    abstract class Abstract<T>(
        private val cacheDataSource: ListCacheDataSource<T>,
    ): FavoriteRepository<T> {

        override suspend fun favoritesList(): List<T>  = cacheDataSource.find("")
    }
}