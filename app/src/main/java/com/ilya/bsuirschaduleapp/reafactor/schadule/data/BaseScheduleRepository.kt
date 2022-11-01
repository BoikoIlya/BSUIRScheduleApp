package com.ilya.bsuirschaduleapp.reafactor.schadule.data

import android.annotation.SuppressLint
import androidx.core.text.isDigitsOnly
import com.ilya.bsuirschaduleapp.reafactor.core.Mapper
import com.ilya.bsuirschaduleapp.reafactor.groupList.data.cache.SelectedScheduleIdDataSource
import com.ilya.bsuirschaduleapp.reafactor.lastscheduleupdate.data.cache.ScheduleUpdateDateCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cache.*
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cloud.CloudScheduleToDomainMapper
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cloud.ScheduleCloudDataSource
import com.ilya.bsuirschaduleapp.reafactor.schadule.domain.ScheduleDomain
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by HP on 25.10.2022.
 **/
class BaseScheduleRepository(
    private val cloudToDomainMapper: CloudScheduleToDomainMapper,
    cacheToDomainMapper: ScheduleCacheToDomainMapper,
    private val toSelectedScheduleIdMapper: ToSelectedScheduleIdMapper,
    private val cloudTeacherSchedule: ScheduleCloudDataSource,
    private val cloudGroupSchedule: ScheduleCloudDataSource,
    private val scheduleCacheDataSource: ScheduleCacheDataSource,
    private val selectedScheduleIdDataSource: SelectedScheduleIdDataSource,
    private val selectedScheduleNameDataSource: SelectedScheduleNameDataSource,
    private val toSelectedScheduleNameMapper: ToSelectedScheduleNameMapper,
    private val scheduleUpdateDateCacheDataSource: ScheduleUpdateDateCacheDataSource
): ScheduleRepository.Abstract<ScheduleCache, ScheduleDomain>(cacheToDomainMapper) {


    @SuppressLint("SimpleDateFormat")
    override suspend fun retrieveData(): ScheduleDomain {
        val selectedScheduleId = selectedScheduleIdDataSource.read()

        val cloud = if(selectedScheduleId.isDigitsOnly()) cloudGroupSchedule.latestSchedule(selectedScheduleId)
        else cloudTeacherSchedule.latestSchedule(selectedScheduleId)

        val data = cloudToDomainMapper.map(cloud)
        scheduleCacheDataSource.save(data)
        selectedScheduleIdDataSource.save(toSelectedScheduleIdMapper.map(data))
        selectedScheduleNameDataSource.save(toSelectedScheduleNameMapper.map(data))
        scheduleUpdateDateCacheDataSource.save(SimpleDateFormat("dd.M.yyyy").format(Date()))
        return data
    }

    override suspend fun cached(): List<ScheduleCache> = scheduleCacheDataSource.read()
}