package com.ilya.bsuirschaduleapp.domain.repositiries

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    suspend fun getDataFromDataStore(key: String): Flow<String>

    suspend fun putDataInDataStore(value:String, key: String)
}