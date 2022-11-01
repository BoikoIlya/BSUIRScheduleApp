package com.ilya.bsuirschaduleapp.reafactor.lastscheduleupdate.data

import android.annotation.SuppressLint
import androidx.core.text.isDigitsOnly
import com.ilya.bsuirschaduleapp.reafactor.groupList.data.cache.SelectedScheduleIdDataSource
import com.ilya.bsuirschaduleapp.reafactor.lastscheduleupdate.data.cache.ScheduleUpdateDateCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.lastscheduleupdate.data.cloud.ScheduleUpdateDateCloudDataSource
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.SelectedScheduleIdRepository
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by HP on 01.11.2022.
 **/
interface ScheduleUpdateDateRepository {

    suspend fun isUpdateNecessary():Boolean

    class Base(
        private val cache: ScheduleUpdateDateCacheDataSource,
        private val cloud: ScheduleUpdateDateCloudDataSource,
        private val cachedId: SelectedScheduleIdDataSource
    ): ScheduleUpdateDateRepository{

        @SuppressLint("SimpleDateFormat")
        override suspend fun isUpdateNecessary(): Boolean {
            val id = cachedId.read()
            val cloudDateStr = if(id.isDigitsOnly()) cloud.lastGroupScheduleUpdate(id)
            else cloud.lastTeacherScheduleUpdate(id)
            val cachedDateStr = cache.read()
            val cloudDate = SimpleDateFormat("dd.M.yyyy").parse(cloudDateStr)
            val cacheDate = SimpleDateFormat("dd.M.yyyy").parse(cachedDateStr)
            return cacheDate?.before(cloudDate) ?: false
        }
    }

}