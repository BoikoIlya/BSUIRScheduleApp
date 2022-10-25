package com.ilya.bsuirschaduleapp.reafactor.schadule.data.cloud

import com.ilya.bsuirschaduleapp.reafactor.core.Mapper
import com.ilya.bsuirschaduleapp.reafactor.schadule.domain.ScheduleDomain

/**
 * Created by HP on 25.10.2022.
 **/
interface CloudScheduleToDomainMapper: Mapper<ScheduleCloud, ScheduleDomain> {

    class Base( private val mapper: ScheduleCloud.Mapper<ScheduleDomain>): CloudScheduleToDomainMapper{
        override fun map(data: ScheduleCloud): ScheduleDomain {
            return data.map(mapper)
        }
    }
}