package com.ilya.bsuirschaduleapp.reafactor.lastscheduleupdate.data.cloud

import com.ilya.bsuirschaduleapp.reafactor.core.CloudDataSource
import com.ilya.bsuirschaduleapp.reafactor.core.DomainErrorHandler
import com.ilya.bsuirschaduleapp.reafactor.core.HandleError

/**
 * Created by HP on 01.11.2022.
 **/
interface ScheduleUpdateDateCloudDataSource {

    suspend fun lastGroupScheduleUpdate(name: String):String
    suspend fun lastTeacherScheduleUpdate(urlId: String):String

    class Base(
        private val service:  ScheduleUpdateDateService,
        @DomainErrorHandler
        handleError: HandleError
    ): ScheduleUpdateDateCloudDataSource, CloudDataSource.Abstract(handleError){
        override suspend fun lastGroupScheduleUpdate(name: String): String = handle {
        service.getLastGroupScheduleUpdate(name).lastUpdateDate.toString()
        }

        override suspend fun lastTeacherScheduleUpdate(urlId: String): String = handle {
            service.getLastTeacherScheduleUpdate(urlId).toString()
        }

    }
}