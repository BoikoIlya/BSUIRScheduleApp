package com.ilya.bsuirschaduleapp.reafactor.schadule.data

import com.ilya.bsuirschaduleapp.reafactor.core.*

/**
 * Created by HP on 25.10.2022.
 **/
interface SubGroupRepository: Read<Int>, Save<Int> {

    interface Read: com.ilya.bsuirschaduleapp.reafactor.core.Read<Int>
    interface Save: com.ilya.bsuirschaduleapp.reafactor.core.Save<Int>
    interface Mutable: Read, Save

    class Base(
        private val cache: PreferenceDataStore<String>,
    ): SubGroupRepository, Read, Save, Mutable{

        companion object{
            const val SELECTED_SUB_GROUP = "selected_sub_group"
        }

        override  fun read(): Int = cache.read(SELECTED_SUB_GROUP).toInt()?:0

        override  fun save(data: Int) = cache.save(data.toString(),SELECTED_SUB_GROUP)

    }
}