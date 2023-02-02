package com.ilya.bsuirschaduleapp.reafactor.schadule.data.cache

import com.ilya.bsuirschaduleapp.reafactor.core.PreferenceDataStoreString
import com.ilya.bsuirschaduleapp.reafactor.core.Read
import com.ilya.bsuirschaduleapp.reafactor.core.Save

/**
 * Created by HP on 31.10.2022.
 **/
interface SelectedScheduleNameDataSource:Save<String>, Read<String> {

    class Base(private val cache: PreferenceDataStoreString): SelectedScheduleNameDataSource{
        companion object{
            const val KEY:String = "selected_schedule_name"
        }
        override fun save(data: String) = cache.save(data, KEY)

        override fun read(): String = cache.read(KEY)

    }
}