package com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.presentation

import androidx.lifecycle.viewModelScope
import com.ilya.bsuirschaduleapp.reafactor.core.*


/**
 * Created by HP on 04.10.2022.
 **/


   abstract class ChangeFavoriteViewModel<T> (
    private val favorites: ChangeFavorite,
    private val updateFavorites: Communication.SuspendUpdate<Boolean>,
    communication: Communication.Mutable<T>,
    private val dispatchers: Dispatchers
    ) : ChangeFavorite, BaseViewModel<T>(communication, dispatchers) {

        override fun changeFavorite(id: String) {
            favorites.changeFavorite(id)
            dispatchers.launchBackground(viewModelScope) { updateFavorites.map(true) }
        }
    }
