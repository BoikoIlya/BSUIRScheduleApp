package com.ilya.bsuirschaduleapp.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ilya.bsuirschaduleapp.utils.Constance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreImpl @Inject constructor(
    private val context: Context
): DataStore {

   private val Context.dataStore by preferencesDataStore(name = Constance.DATA_STORE_NAME)

    override suspend fun saveData(value: String,key: String) {
        val myKey = stringPreferencesKey(key)
        context.dataStore.edit { settings->
            settings[myKey] = value
        }
    }

    override suspend fun readData(key: String): Flow<String> {
        val myKey = stringPreferencesKey(key)
        return context.dataStore.data.map{ it[myKey] ?: Constance.DATA_STORE_DEF_VALUE }
    }

}