package com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.presentation

import com.ilya.bsuirschaduleapp.reafactor.core.ChangeFavorite
import com.ilya.bsuirschaduleapp.reafactor.core.Dispatchers
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.data.FavoriteRepository
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListItemUi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by HP on 08.10.2022.
 **/
@HiltViewModel
class BaseFavoriteTeachersViewModel @Inject constructor(
    private val updateFavorites: UpdateFavoritesTeachers.Mutable,
    favorites: FavoriteTeachersCommunication,
    dispatchers: Dispatchers,
    repository: FavoriteRepository<TeacherListItemUi>,
    changeFavorite: ChangeFavorite
): FavoritesViewModel.Abstract<TeacherListItemUi>
    (updateFavorites, favorites, dispatchers, repository,changeFavorite)