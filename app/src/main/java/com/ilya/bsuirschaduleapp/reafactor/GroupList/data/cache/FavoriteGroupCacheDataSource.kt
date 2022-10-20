package com.ilya.bsuirschaduleapp.reafactor.GroupList.data.cache

import com.ilya.bsuirschaduleapp.reafactor.core.FavoriteListCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.core.PreferenceDataStore

/**
 * Created by HP on 08.10.2022.
 **/
interface FavoriteGroupCacheDataSource: FavoriteListCacheDataSource {

    class Base(key: String, favorites: PreferenceDataStore<Set<String>>):
        FavoriteListCacheDataSource.Abstract(favorites,key),
        FavoriteGroupCacheDataSource
}