package com.ilya.bsuirschaduleapp.reafactor.core

/**
 * Created by HP on 23.09.2022.
 **/
abstract class AbstractListCacheDataSource<T, R>(
    private val mapper: Mapper<List<T>, List<R>>
) : ListCacheDataSource<T> {


    protected abstract suspend fun saveItem(data: R)

    override suspend fun save(data: List<T>) = mapper.map(data).forEach { saveItem(it) }


}



