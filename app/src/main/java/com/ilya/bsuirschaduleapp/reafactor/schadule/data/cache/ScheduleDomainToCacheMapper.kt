package com.ilya.bsuirschaduleapp.reafactor.schadule.data.cache

import com.ilya.bsuirschaduleapp.reafactor.core.Mapper
import com.ilya.bsuirschaduleapp.reafactor.schadule.domain.ScheduleDomain

/**
 * Created by HP on 25.10.2022.
 **/
interface ScheduleDomainToCacheMapper: Mapper<ScheduleDomain, List<ScheduleCache>> {

    class Base(private val mapper: ScheduleDomain.Mapper<List<ScheduleCache>>): ScheduleDomainToCacheMapper{

        override fun map(data: ScheduleDomain): List<ScheduleCache> {
          return data.map(mapper)
        }

    }
}