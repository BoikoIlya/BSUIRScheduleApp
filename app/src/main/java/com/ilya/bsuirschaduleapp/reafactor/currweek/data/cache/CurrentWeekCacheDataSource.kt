package com.ilya.bsuirschaduleapp.reafactor.currweek.data.cache

import com.ilya.bsuirschaduleapp.reafactor.core.PreferenceDataStoreString
import com.ilya.bsuirschaduleapp.reafactor.core.Read
import com.ilya.bsuirschaduleapp.reafactor.core.Save

/**
 * Created by HP on 01.11.2022.
 **/
interface CurrentWeekCacheDataSource: Read<String>, Save<String> {

    class Base(private val cache: PreferenceDataStoreString): CurrentWeekCacheDataSource{
        companion object{
            const val key: String = "current_week"
        }

        override fun read(): String = cache.read(key)

        override fun save(data: String) = cache.save(data, key)

    }
}