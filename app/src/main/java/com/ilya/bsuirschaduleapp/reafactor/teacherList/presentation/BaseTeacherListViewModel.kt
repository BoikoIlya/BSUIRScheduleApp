package com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation

import com.ilya.bsuirschaduleapp.reafactor.core.ChangeFavorite
import com.ilya.bsuirschaduleapp.reafactor.core.Dispatchers
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.presentation.UpdateFavoritesTeachers
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.ListInteractor
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.TeacherListItemDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by HP on 08.10.2022.
 **/
@HiltViewModel
class BaseTeacherListViewModel @Inject constructor(
    progressCommunication: TeacherListProgressCommunication,
    communication: TeacherListCommunication,
    teacherListInteractor: ListInteractor<TeacherListItemDomain, TeacherListItemUi>,
    dispatchers: Dispatchers,
    changeFavorite: ChangeFavorite,
    updateFavorites: UpdateFavoritesTeachers.Update
) : ListViewModel.Abstract<TeacherListItemDomain, TeacherListItemUi>(
    progressCommunication,
    communication,
    teacherListInteractor,
    dispatchers,
    changeFavorite,
    updateFavorites
)