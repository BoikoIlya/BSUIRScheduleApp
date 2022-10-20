package com.ilya.bsuirschaduleapp.reafactor.favoriteGroups.presentation

import com.ilya.bsuirschaduleapp.reafactor.GroupList.data.GroupListRepository
import com.ilya.bsuirschaduleapp.reafactor.GroupList.presentation.GroupListItemUi
import com.ilya.bsuirschaduleapp.reafactor.core.ChangeFavorite
import com.ilya.bsuirschaduleapp.reafactor.core.Dispatchers
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.data.FavoriteRepository
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.presentation.FavoritesViewModel
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListItemUi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by HP on 08.10.2022.
 **/
@HiltViewModel
class BaseFavoriteGroupsViewModel @Inject constructor(
     updateFavorites: UpdateFavoritesGroups.Mutable,
    favorites: FavoriteGroupsCommunication,
    dispatchers: Dispatchers,
    repository: FavoriteRepository<GroupListItemUi>,
    changeFavorite: GroupListRepository
): FavoritesViewModel.Abstract<GroupListItemUi>
    (updateFavorites, favorites, dispatchers, repository,changeFavorite)