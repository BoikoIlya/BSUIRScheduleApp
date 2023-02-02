package com.ilya.bsuirschaduleapp.reafactor.schadule.data

import androidx.core.text.isDigitsOnly
import com.ilya.bsuirschaduleapp.reafactor.core.*
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cache.ScheduleCache
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cache.ScheduleCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cloud.ScheduleCloud
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cloud.ScheduleCloudDataSource
import com.ilya.bsuirschaduleapp.reafactor.schadule.domain.ScheduleDomain
import javax.inject.Inject

/**
 * Created by HP on 25.10.2022.
 **/


interface ScheduleRepository<T, R>: RefreshRepository<R> {

    abstract class Abstract<T, R>(
        private val mapper: Mapper<List<T>, R>,
    ): ScheduleRepository<T, R> {

        override suspend fun fetchData(): R {
            val cache = cached()
            if (cache.isNotEmpty()) {
                return mapper.map(cache)
            }
            return retrieveData()
        }

        override suspend fun refresh(): R = retrieveData()

        protected abstract suspend fun retrieveData(): R
        protected abstract suspend fun cached(): List<T>
    }
}