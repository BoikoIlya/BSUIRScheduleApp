package com.ilya.bsuirschaduleapp.data.repositories

import com.ilya.bsuirschaduleapp.data.local.datastore.DataStore
import com.ilya.bsuirschaduleapp.domain.repositiries.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore
): DataStoreRepository {

    override suspend fun getDataFromDataStore(key: String): Flow<String> {
        return dataStore.readData(key)
    }

    override suspend fun putDataInDataStore(value: String, key: String) {
        dataStore.saveData(value, key)
    }
}