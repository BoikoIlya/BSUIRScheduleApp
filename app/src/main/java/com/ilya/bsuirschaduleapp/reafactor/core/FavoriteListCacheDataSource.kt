package com.ilya.bsuirschaduleapp.reafactor.core


/**
 * Created by HP on 01.10.2022.
 **/
interface FavoriteListCacheDataSource: ChangeFavorite, IsFavorite {


   abstract class Abstract(
        private val favorites: PreferenceDataStore<Set<String>>,
        private val key: String
    ): FavoriteListCacheDataSource{

        private var cache: Set<String> = favorites.read(key)

        override fun changeFavorite(id: String) {
           val data = cache.toMutableSet()
            if(isFavorite(id)) data.remove(id)
            else data.add(id)
            favorites.save(data, key)
            cache = favorites.read(key)
        }

        override fun isFavorite(id: String): Boolean = cache.contains(id)
    }


}