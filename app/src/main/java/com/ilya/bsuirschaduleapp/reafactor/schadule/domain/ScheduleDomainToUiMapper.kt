package com.ilya.bsuirschaduleapp.reafactor.schadule.domain

import com.ilya.bsuirschaduleapp.reafactor.core.Mapper
import com.ilya.bsuirschaduleapp.reafactor.schadule.presentation.ScheduleUi

/**
 * Created by HP on 25.10.2022.
 **/
interface ScheduleDomainToUiMapper: Mapper<ScheduleDomain, ScheduleUi> {

    class Base(private val mapper: ScheduleDomain.Mapper<ScheduleUi>): ScheduleDomainToUiMapper{
        override fun map(data: ScheduleDomain): ScheduleUi {
           return data.map(mapper)
        }
    }
}