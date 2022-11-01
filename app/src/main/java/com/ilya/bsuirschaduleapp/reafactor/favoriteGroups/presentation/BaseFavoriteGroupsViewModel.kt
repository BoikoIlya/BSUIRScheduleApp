package com.ilya.bsuirschaduleapp.reafactor.favoriteGroups.presentation

import com.ilya.bsuirschaduleapp.reafactor.groupList.data.GroupListRepository
import com.ilya.bsuirschaduleapp.reafactor.groupList.presentation.GroupListItemUi
import com.ilya.bsuirschaduleapp.reafactor.core.Dispatchers
import com.ilya.bsuirschaduleapp.reafactor.favoriteGroups.domain.FavoriteGroupInteractor
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.presentation.FavoritesViewModel
import com.ilya.bsuirschaduleapp.reafactor.groupList.domain.GroupListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.groupList.presentation.GroupListProgressCommunication
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
  //  repository: FavoriteRepository<GroupListItem>,
    changeFavorite: GroupListRepository,
     interactor: FavoriteGroupInteractor,
     progressCommunication:GroupListProgressCommunication,
     //selectedScheduleRepository: SelectedScheduleRepository
): FavoritesViewModel.Abstract<GroupListItemDomain, GroupListItemUi>
    (
    updateFavorites,
    favorites,
    dispatchers,
   // repository,
    changeFavorite,
    interactor,
    progressCommunication,
    //selectedScheduleRepository
)