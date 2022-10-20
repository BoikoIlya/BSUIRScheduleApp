package com.ilya.bsuirschaduleapp.reafactor.GroupList.presentation

import com.ilya.bsuirschaduleapp.reafactor.GroupList.data.GroupListRepository
import com.ilya.bsuirschaduleapp.reafactor.GroupList.domain.GroupListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.core.ChangeFavorite
import com.ilya.bsuirschaduleapp.reafactor.core.Dispatchers
import com.ilya.bsuirschaduleapp.reafactor.favoriteGroups.data.GroupListFavoriteRepository
import com.ilya.bsuirschaduleapp.reafactor.favoriteGroups.presentation.UpdateFavoritesGroups
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.presentation.UpdateFavoritesTeachers
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.ListInteractor
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.TeacherListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.ListViewModel
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListCommunication
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListProgressCommunication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by HP on 08.10.2022.
 **/
@HiltViewModel
class BaseGroupListViewModel @Inject constructor(
    progressCommunication: GroupListProgressCommunication,
    communication: GroupListCommunication,
    groupListInteractor: ListInteractor<GroupListItemDomain, GroupListItemUi>,
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
