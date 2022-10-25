package com.ilya.bsuirschaduleapp.reafactor.schadule.data

import androidx.core.text.isDigitsOnly
import com.ilya.bsuirschaduleapp.reafactor.core.Mapper
import com.ilya.bsuirschaduleapp.reafactor.core.PreferenceDataStore
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cache.ScheduleCache
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cache.ScheduleCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cloud.CloudScheduleToDomainMapper
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cloud.ScheduleCloudDataSource
import com.ilya.bsuirschaduleapp.reafactor.schadule.domain.ScheduleDomain

/**
 * Created by HP on 25.10.2022.
 **/
class BaseScheduleRepository(
    private val cloudToDomainMapper: CloudScheduleToDomainMapper,
    cacheToDomainMapper: Mapper<List<ScheduleCache>, ScheduleDomain>,
    private val toSelectedScheduleMapper: Mapper<ScheduleDomain, String>,
    private val cloudTeacherSchedule: ScheduleCloudDataSource,
    private val cloudGroupSchedule: ScheduleCloudDataSource,
    private val scheduleCacheDataSource: ScheduleCacheDataSource,
    private val selectedSchedulePreference: PreferenceDataStore<String>
): ScheduleRepository.Abstract<ScheduleCache, ScheduleDomain>(cacheToDomainMapper) {

    companion object{
        const val SELECTED_SCHEDULE = "selected_schedule"
    }

    override suspend fun retrieveData(): ScheduleDomain {
        val selectedSchedule = selectedSchedulePreference.read(SELECTED_SCHEDULE)

        val cloud = if(selectedSchedule.isDigitsOnly()) cloudGroupSchedule.latestSchedule(selectedSchedule)
        else cloudTeacherSchedule.latestSchedule(selectedSchedule)

        val data = cloudToDomainMapper.map(cloud)
        scheduleCacheDataSource.save(data)
        selectedSchedulePreference.save(toSelectedScheduleMapper.map(data), SELECTED_SCHEDULE)
        return data
    }

    override suspend fun cached(): List<ScheduleCache> = scheduleCacheDataSource.read()
}