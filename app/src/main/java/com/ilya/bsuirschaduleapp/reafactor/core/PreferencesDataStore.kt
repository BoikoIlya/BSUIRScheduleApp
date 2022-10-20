package com.ilya.bsuirschaduleapp.reafactor.core

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

/**
 * Created by HP on 28.09.2022.
 **/
interface PreferencesDataStore<T>: Save<T>, Read<Flow<T>> {

    abstract class Abstract<T>(
        private val dataStore: DataStore<Preferences>,
        private val prefsKey: Preferences.Key<T>,
        private val defaultValue: T
    ):PreferencesDataStore<T>{

        override suspend fun read(): Flow<T> = dataStore.data.map { prefs-> prefs[prefsKey] ?: defaultValue}

        override suspend fun save(data: T) {dataStore.edit { prefs-> prefs[prefsKey] = data }}


    }
}