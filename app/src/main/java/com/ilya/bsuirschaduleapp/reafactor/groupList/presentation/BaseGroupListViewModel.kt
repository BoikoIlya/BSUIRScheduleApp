package com.ilya.bsuirschaduleapp.reafactor.groupList.presentation

import com.ilya.bsuirschaduleapp.reafactor.groupList.data.GroupListRepository
import com.ilya.bsuirschaduleapp.reafactor.groupList.domain.GroupListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.core.Dispatchers
import com.ilya.bsuirschaduleapp.reafactor.favoriteGroups.presentation.UpdateFavoritesGroups
import com.ilya.bsuirschaduleapp.reafactor.groupList.domain.GroupListInteractor
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.ListInteractor
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.ListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by HP on 08.10.2022.
 **/
@HiltViewModel
class BaseGroupListViewModel @Inject constructor(
    progressCommunication: GroupListProgressCommunication,
    communication: GroupListCommunication,
    groupListInteractor: GroupListInteractor,
    dispatchers: Dispatchers,
    changeFavorite: GroupListRepository,
    updateFavorites: UpdateFavoritesGroups.Update
) : ListViewModel.Abstract<GroupListItemDomain, GroupListItemUi>(
    progressCommunication,
    communication,
    groupListInteractor,
    dispatchers,
    changeFavorite,
    updateFavorites
)
