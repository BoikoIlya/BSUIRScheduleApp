package com.ilya.bsuirschaduleapp.reafactor.core

import android.content.SharedPreferences
import kotlinx.coroutines.flow.Flow

/**
 * Created by HP on 04.10.2022.
 **/
class PreferenceDataStoreSet(
    private val sharedPreferences: SharedPreferences
): PreferenceDataStore<Set<String>> {

    override fun read(key: String): Set<String> = sharedPreferences.getStringSet(key, emptySet()) ?: emptySet()

    override fun save(data: Set<String>, key: String) = sharedPreferences.edit().putStringSet(key, data).apply()


}