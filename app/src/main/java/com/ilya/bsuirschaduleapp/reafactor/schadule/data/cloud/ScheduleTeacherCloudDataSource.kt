package com.ilya.bsuirschaduleapp.reafactor.schadule.data.cloud

import androidx.room.Query
import com.ilya.bsuirschaduleapp.reafactor.core.CloudDataSource
import com.ilya.bsuirschaduleapp.reafactor.core.DomainErrorHandler
import com.ilya.bsuirschaduleapp.reafactor.core.HandleError
import javax.inject.Inject

/**
 * Created by HP on 25.10.2022.
 **/

interface ScheduleCloudDataSource: CloudDataSource{
    suspend fun latestSchedule(query: String): ScheduleCloud
}

interface ScheduleTeacherCloudDataSource: ScheduleCloudDataSource {

    class Base @Inject constructor(
        private val scheduleService: ScheduleService,
        @DomainErrorHandler
        handleError: HandleError
    ): ScheduleTeacherCloudDataSource, CloudDataSource.Abstract(handleError){

        override suspend fun latestSchedule(query: String): ScheduleCloud = handle {
            return@handle scheduleService.getScheduleByTeacherUrlId(query)
        }

    }
}

interface ScheduleGroupCloudDataSource: ScheduleCloudDataSource {

    class Base @Inject constructor(
        private val scheduleService: ScheduleService,
        @DomainErrorHandler
        handleError: HandleError
    ): ScheduleGroupCloudDataSource, CloudDataSource.Abstract(handleError){

        override suspend fun latestSchedule(query: String): ScheduleCloud = handle {
            return@handle scheduleService.getScheduleByGroupNumber(query)
        }

    }
}