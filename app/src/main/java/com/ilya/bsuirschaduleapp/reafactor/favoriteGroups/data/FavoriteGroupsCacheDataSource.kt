package com.ilya.bsuirschaduleapp.reafactor.favoriteGroups.data

import com.ilya.bsuirschaduleapp.reafactor.core.FavoriteListCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.core.PreferenceDataStore
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.data.FavoritesTeachersCacheDataSource

/**
 * Created by HP on 09.10.2022.
 **/


interface FavoriteGroupsCacheDataSource: FavoriteListCacheDataSource {

    class Base(key: String, favorites: PreferenceDataStore<Set<String>>):
        FavoriteListCacheDataSource.Abstract(favorites,key),
        FavoriteGroupsCacheDataSource
}