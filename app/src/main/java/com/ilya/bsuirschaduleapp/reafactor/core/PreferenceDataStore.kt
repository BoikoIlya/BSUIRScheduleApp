package com.ilya.bsuirschaduleapp.reafactor.core

/**
 * Created by HP on 04.10.2022.
 **/
interface PreferenceDataStore<T> {

    fun read(key: String): T

    fun save(data: T, key: String)
}