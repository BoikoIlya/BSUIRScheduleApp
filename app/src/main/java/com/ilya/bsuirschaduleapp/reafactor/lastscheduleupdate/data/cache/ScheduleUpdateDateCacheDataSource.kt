package com.ilya.bsuirschaduleapp.reafactor.lastscheduleupdate.data.cache

import com.ilya.bsuirschaduleapp.reafactor.core.*

/**
 * Created by HP on 01.11.2022.
 **/

interface ScheduleUpdateDateCacheDataSource: CacheDataSource<String> {

    companion object{
        const val key: String = "schedule_update_date"
    }

    class Base(
        cache: PreferenceDataStoreString,
    ): CacheDataSource.Abstract<String>(cache, key), ScheduleUpdateDateCacheDataSource
}