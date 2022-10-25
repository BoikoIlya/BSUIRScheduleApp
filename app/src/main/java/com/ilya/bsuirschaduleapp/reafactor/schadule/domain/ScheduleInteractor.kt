package com.ilya.bsuirschaduleapp.reafactor.schadule.domain

import com.ilya.bsuirschaduleapp.reafactor.core.*
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.ScheduleRepository
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.SubGroupRepository
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cache.ScheduleCache
import com.ilya.bsuirschaduleapp.reafactor.schadule.presentation.ScheduleUi

/**
 * Created by HP on 25.10.2022.
 **/
interface ScheduleInteractor: FetchDataInteractor<ScheduleDomain, ScheduleUi> {


    class BaseScheduleInteractor(
        @UiErrorHandler
        handleError: HandleError,
        dispatchers: Dispatchers,
        private val scheduleRepository: ScheduleRepository<ScheduleCache, ScheduleDomain>,
        private val mapper: ScheduleDomainToUi
    ) : FetchDataInteractor.Abstract<ScheduleDomain, ScheduleUi>(handleError, dispatchers,mapper,scheduleRepository),
        ScheduleInteractor
}
