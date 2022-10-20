package com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.data

import com.ilya.bsuirschaduleapp.reafactor.core.ListCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.core.Mapper
import kotlinx.coroutines.flow.first

/**
 * Created by HP on 02.10.2022.
 **/
interface FavoriteRepository<R> {

    suspend fun favoritesList(): List<R>

    abstract class Abstract<T,R>(
        private val cacheDataSource: ListCacheDataSource<T>,
        private val mapper: Mapper<List<T>,List<R>>,
    ): FavoriteRepository<R> {

        override suspend fun favoritesList(): List<R>  =
            mapper.map(cacheDataSource.find("").first())
    }
}