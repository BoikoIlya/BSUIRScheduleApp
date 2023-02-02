package com.ilya.bsuirschaduleapp.reafactor.core

/**
 * Created by HP on 01.11.2022.
 **/
interface CacheDataSource<T>:Read<T>, Save<T>{

    abstract class Abstract<T>(
        private val cache: PreferenceDataStore<T>,
        private val key: String
    ): CacheDataSource<T>{

        override fun save(data: T) = cache.save(data, key)

        override fun read(): T  = cache.read(key)

    }
}