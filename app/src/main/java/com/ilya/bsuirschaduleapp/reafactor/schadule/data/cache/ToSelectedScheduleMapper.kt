package com.ilya.bsuirschaduleapp.reafactor.schadule.data.cache

import com.ilya.bsuirschaduleapp.reafactor.core.Mapper
import com.ilya.bsuirschaduleapp.reafactor.schadule.domain.ScheduleDomain

/**
 * Created by HP on 25.10.2022.
 **/
interface ToSelectedScheduleMapper:Mapper<ScheduleDomain, String>  {

    class Base(private val mapper: ScheduleDomain.Mapper<String>): ToSelectedScheduleMapper{
        override fun map(data: ScheduleDomain): String {
         return data.map(mapper)
        }
    }
}