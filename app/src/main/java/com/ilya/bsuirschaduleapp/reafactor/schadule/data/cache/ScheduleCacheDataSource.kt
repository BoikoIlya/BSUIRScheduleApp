package com.ilya.bsuirschaduleapp.reafactor.schadule.data.cache

import com.ilya.bsuirschaduleapp.reafactor.core.SuspendRead
import com.ilya.bsuirschaduleapp.reafactor.core.SuspendSave
import com.ilya.bsuirschaduleapp.reafactor.core.ScheduleDao
import com.ilya.bsuirschaduleapp.reafactor.schadule.domain.ScheduleDomain
import javax.inject.Inject

/**
 * Created by HP on 25.10.2022.
 **/
interface ScheduleCacheDataSource: SuspendSave<ScheduleDomain>, SuspendRead<List<ScheduleCache>> {

    class Base @Inject constructor(
        private val scheduleDao: ScheduleDao,
        private val mapper: ScheduleDomainToCacheMapper,
    ): ScheduleCacheDataSource{

        override suspend fun save(data: ScheduleDomain) {
            scheduleDao.clearScheduleTable()
            mapper.map(data).forEach { scheduleDao.insertSchedule(it) }
        }

        override suspend fun read(): List<ScheduleCache> {
             return scheduleDao.getSchedules()
        }

    }
}