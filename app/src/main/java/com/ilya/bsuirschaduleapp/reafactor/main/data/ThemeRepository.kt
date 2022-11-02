package com.ilya.bsuirschaduleapp.reafactor.main.data

import com.ilya.bsuirschaduleapp.reafactor.core.CacheDataSource

/**
 * Created by HP on 02.11.2022.
 **/
interface ThemeRepository {
     fun saveTheme(value: Boolean)
     fun readTheme(): Boolean

    class Base(
        private val cache: CacheDataSource<String>
    ): ThemeRepository{
        override  fun saveTheme(value: Boolean) = cache.save(value.toString())

        override fun readTheme(): Boolean = cache.read().toBoolean()

    }

}