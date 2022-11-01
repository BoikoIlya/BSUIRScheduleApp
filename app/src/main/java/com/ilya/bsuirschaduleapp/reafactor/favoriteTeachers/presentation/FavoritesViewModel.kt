package com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.presentation

import androidx.lifecycle.LifecycleOwner
import com.ilya.bsuirschaduleapp.reafactor.core.*
import kotlinx.coroutines.flow.FlowCollector

/**
 * Created by HP on 02.10.2022.
 **/
interface FavoritesViewModel {

    suspend fun collectUpdate(lifecycleOwner: LifecycleOwner, flowCollector: FlowCollector<Boolean>)


   abstract class Abstract<S,T>(
        private val updateFavorites: Communication.MutableSingle<Boolean>,
        private val favorites: Communication.Mutable<List<T>>,
        private val dispatchers: Dispatchers,
        //private val repository: FavoriteRepository<T>,
        changeFavorite: ChangeFavorite,
        interactor: FetchDataInteractor<List<S>,List<T>>,
        progressCommunication: Communication.Mutable<Boolean>,
    ) : ChangeFavoriteViewModel<S,T>(
       changeFavorite,
       updateFavorites,
       favorites,
       dispatchers,
       interactor,
       progressCommunication
   ),
    FavoritesViewModel{




       override suspend fun collectUpdate(
            lifecycleOwner: LifecycleOwner,
            flowCollector: FlowCollector<Boolean>
        ) = updateFavorites.collect(lifecycleOwner, flowCollector)

    }
}

