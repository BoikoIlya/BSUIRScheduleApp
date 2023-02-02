package com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.data

import com.ilya.bsuirschaduleapp.reafactor.core.FavoriteListCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.core.PreferenceDataStore

/**
 * Created by HP on 04.10.2022.
 **/
interface FavoritesTeachersCacheDataSource: FavoriteListCacheDataSource {

    class Base(key: String, favorites: PreferenceDataStore<Set<String>>):
        FavoriteListCacheDataSource.Abstract(favorites,key),
        FavoritesTeachersCacheDataSource
}