package com.ilya.bsuirschaduleapp.reafactor.core

/**
 * Created by HP on 22.10.2022.
 **/
interface RefreshRepository<T>: FetchDataRepository<T> {

    suspend fun refresh(): T
}