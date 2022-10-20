package com.ilya.bsuirschaduleapp.reafactor.core


import kotlinx.coroutines.flow.Flow

/**
 * Created by HP on 17.09.2022.
 **/
abstract class AbstractRepository<T, R>(
    private val mapper: Mapper<List<T>,List<R>>,
    private val cacheDataSource: ListCacheDataSource<R>,
    private val favoriteCacheDataSource: FavoriteListCacheDataSource,
): Repository<List<R>> {

    override suspend fun fetchData(query: String): List<R> {
        val cloudData = cloudData(query)
        val data = mapper.map(cloudData)
        saveData(data)
        return data
    }

    abstract suspend fun cloudData(query: String):List<T>
    abstract suspend fun saveData(data: List<R>)

    override suspend fun find(query: String): Flow<List<R>> = cacheDataSource.find(query)

    override  fun changeFavorite(id: String) = favoriteCacheDataSource.changeFavorite(id)
}