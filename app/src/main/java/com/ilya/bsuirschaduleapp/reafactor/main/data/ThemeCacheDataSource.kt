package com.ilya.bsuirschaduleapp.reafactor.main.data

import com.ilya.bsuirschaduleapp.reafactor.core.CacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.core.PreferenceDataStoreString

/**
 * Created by HP on 02.11.2022.
 **/
interface ThemeCacheDataSource: CacheDataSource<String> {

    companion object{
        const val key: String = "theme"
    }

    class Base(
        cache: PreferenceDataStoreString,
    ): ThemeCacheDataSource, CacheDataSource.Abstract<String>(cache, key)
}