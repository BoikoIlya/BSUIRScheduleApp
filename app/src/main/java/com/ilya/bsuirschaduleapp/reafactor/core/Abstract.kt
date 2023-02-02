package com.ilya.bsuirschaduleapp.reafactor.core

/**
 * Created by HP on 17.09.2022.
 **/
    interface Mapper<S, R> {

        fun map(data: S): R

        interface Unit<T> :Mapper<T, kotlin.Unit>
    }

    interface SuspendSave<T> {
        suspend fun save(data: T)
    }

    interface SuspendRead<T> {
        suspend fun read(): T
    }

    interface Save<T> {
      fun save(data: T)
    }

    interface Read<T> {
         fun read(): T
    }

    interface Find<T> {
        fun find(query: String): T
    }

    interface SuspendFind<T> {
        suspend fun find(query: String): T
    }

    interface IsFavorite{
        fun isFavorite(id: String): Boolean
    }

