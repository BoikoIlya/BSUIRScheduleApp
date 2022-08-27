package com.ilya.bsuirschaduleapp.data.local.datastore

import kotlinx.coroutines.flow.Flow

interface DataStore {

    suspend fun saveData(value: String, key: String)

    suspend fun readData(key: String): Flow<String>
}