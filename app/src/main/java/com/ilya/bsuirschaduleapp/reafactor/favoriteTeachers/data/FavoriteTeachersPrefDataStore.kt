package com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.data


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.ilya.bsuirschaduleapp.reafactor.core.PreferencesDataStore

/**
 * Created by HP on 01.10.2022.
 **/
interface FavoriteTeachersPrefDataStore: PreferencesDataStore<Set<String>> {
    class Base (
        private val dataStore: DataStore<Preferences>,
        private val key: Preferences.Key<Set<String>>,
        private val defaultValue: Set<String>
    ): PreferencesDataStore.Abstract<Set<String>>(dataStore, key, defaultValue),
        FavoriteTeachersPrefDataStore
}

