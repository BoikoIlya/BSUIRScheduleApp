package com.ilya.bsuirschaduleapp.reafactor.core

import android.content.SharedPreferences

/**
 * Created by HP on 25.10.2022.
 **/
class PreferenceDataStoreString(
    private val sharedPreferences: SharedPreferences
): PreferenceDataStore<String> {

    override fun read(key: String): String = sharedPreferences.getString(key, "")?:""

    override fun save(data: String, key: String) = sharedPreferences.edit().putString(key, data).apply()
}