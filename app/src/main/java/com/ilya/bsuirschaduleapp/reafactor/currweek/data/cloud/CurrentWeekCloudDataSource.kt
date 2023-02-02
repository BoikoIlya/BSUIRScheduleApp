package com.ilya.bsuirschaduleapp.reafactor.currweek.data.cloud

import com.ilya.bsuirschaduleapp.reafactor.core.CloudDataSource
import com.ilya.bsuirschaduleapp.reafactor.core.DomainErrorHandler
import com.ilya.bsuirschaduleapp.reafactor.core.HandleError

/**
 * Created by HP on 01.11.2022.
 **/
interface CurrentWeekCloudDataSource {

    suspend fun currentWeek(): String

    class Base(
        private val service: CurrentWeekService,
    ): CurrentWeekCloudDataSource{

        override suspend fun currentWeek(): String = service.getCurrentWeek().toString()
    }
}