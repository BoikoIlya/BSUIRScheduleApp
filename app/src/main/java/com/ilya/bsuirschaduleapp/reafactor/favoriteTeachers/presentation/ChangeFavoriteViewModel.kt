package com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.presentation

import androidx.lifecycle.viewModelScope
import com.ilya.bsuirschaduleapp.reafactor.core.*
import com.ilya.bsuirschaduleapp.reafactor.schadule.presentation.ScheduleProgressCommunication


/**
 * Created by HP on 04.10.2022.
 **/


   abstract class ChangeFavoriteViewModel<S,T> (
    private val favorites: ChangeFavorite,
    private val updateFavorites: Communication.SuspendUpdate<Boolean>,
    communication: Communication.Mutable<List<T>>,
    private val dispatchers: Dispatchers,
    interactor: FetchDataInteractor<List<S>, List<T>>,
    progressCommunication: Communication.Mutable<Boolean>
    ) : ChangeFavorite, FetchDataViewModel.Abstract<List<S>,List<T>>(
    communication =  communication,
    dispatchers,
    interactor,
    progressCommunication = progressCommunication
) {

        override fun changeFavorite(id: String) {
            favorites.changeFavorite(id)
            dispatchers.launchBackground(viewModelScope) { updateFavorites.map(true) }
        }
    }
