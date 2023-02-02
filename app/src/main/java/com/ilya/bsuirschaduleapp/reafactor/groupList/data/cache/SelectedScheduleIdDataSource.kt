package com.ilya.bsuirschaduleapp.reafactor.groupList.data.cache

import com.ilya.bsuirschaduleapp.reafactor.core.PreferenceDataStore
import com.ilya.bsuirschaduleapp.reafactor.core.Read
import com.ilya.bsuirschaduleapp.reafactor.core.Save

/**
 * Created by HP on 26.10.2022.
 **/
interface SelectedScheduleIdDataSource: Save<String>, Read<String> {

    class Base(
        private val cache: PreferenceDataStore<String>
        ): SelectedScheduleIdDataSource{

        companion object{
            const val KEY = "selected_schedule"
        }

        override fun save(data: String) = cache.save(data, KEY)

        override fun read(): String  = cache.read(KEY)

    }
}