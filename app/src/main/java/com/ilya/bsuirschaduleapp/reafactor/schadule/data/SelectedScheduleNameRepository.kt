package com.ilya.bsuirschaduleapp.reafactor.schadule.data

import com.ilya.bsuirschaduleapp.reafactor.core.Read
import com.ilya.bsuirschaduleapp.reafactor.core.Save
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cache.SelectedScheduleNameDataSource

/**
 * Created by HP on 31.10.2022.
 **/
interface SelectedScheduleNameRepository: Read<String>, Save<String> {

    class Base(private val cache: SelectedScheduleNameDataSource): SelectedScheduleNameRepository {
        override fun read(): String = cache.read()

        override fun save(data: String) = cache.save(data)

    }
}