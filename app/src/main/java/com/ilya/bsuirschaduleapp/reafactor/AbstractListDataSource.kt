package com.ilya.bsuirschaduleapp.reafactor

import com.ilya.bsuirschaduleapp.reafactor.core.ListCacheDataSource

/**
 * Created by HP on 23.09.2022.
 **/
abstract class AbstractListDataSource<T> : ListCacheDataSource<T> {

    protected abstract suspend fun saveItem(data: T)

    override suspend fun save(data: List<T>) = data.forEach { saveItem(it) }


}



