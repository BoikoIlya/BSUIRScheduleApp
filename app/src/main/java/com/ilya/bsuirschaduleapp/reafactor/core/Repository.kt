package com.ilya.bsuirschaduleapp.reafactor.core


import kotlinx.coroutines.flow.Flow

/**
 * Created by HP on 17.09.2022.
 **/
interface Repository<T>: SearchRepository<Flow<T>>, ChangeFavorite {

    suspend fun fetchData(query: String):T
}