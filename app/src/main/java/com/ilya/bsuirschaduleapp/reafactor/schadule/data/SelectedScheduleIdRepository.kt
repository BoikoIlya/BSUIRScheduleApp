package com.ilya.bsuirschaduleapp.reafactor.schadule.data

import com.ilya.bsuirschaduleapp.reafactor.core.Read
import com.ilya.bsuirschaduleapp.reafactor.core.Save
import com.ilya.bsuirschaduleapp.reafactor.groupList.data.cache.SelectedScheduleIdDataSource

/**
 * Created by HP on 31.10.2022.
 **/
interface SelectedScheduleIdRepository: Read<String>, Save<String> {

    class Base(private val cache: SelectedScheduleIdDataSource): SelectedScheduleIdRepository {
        override fun read(): String = cache.read()

        override fun save(data: String) = cache.save(data)

    }
}