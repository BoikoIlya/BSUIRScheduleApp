package com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.ilya.bsuirschaduleapp.reafactor.core.ChangeFavorite
import com.ilya.bsuirschaduleapp.reafactor.core.Communication
import com.ilya.bsuirschaduleapp.reafactor.core.Dispatchers
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.data.FavoriteRepository
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListItemUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

/**
 * Created by HP on 02.10.2022.
 **/
interface FavoritesViewModel {

    suspend fun collectUpdate(lifecycleOwner: LifecycleOwner, flowCollector: FlowCollector<Boolean>)

    fun update(): Job

   abstract class Abstract<T>(
        private val updateFavorites: Communication.MutableSingle<Boolean>,
        private val favorites: Communication.Mutable<List<T>>,
        private val dispatchers: Dispatchers,
        private val repository: FavoriteRepository<T>,
        changeFavorite: ChangeFavorite
    ) : ChangeFavoriteViewModel<List<T>>(changeFavorite, updateFavorites, favorites, dispatchers),
    FavoritesViewModel{

        init {
            update()
        }

        final override fun update(): Job =
            dispatchers.launchBackground(viewModelScope) { favorites.map(repository.favoritesList()) }

       override suspend fun collectUpdate(
            lifecycleOwner: LifecycleOwner,
            flowCollector: FlowCollector<Boolean>
        ) = updateFavorites.collect(lifecycleOwner, flowCollector)

    }
}

