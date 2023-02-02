package com.ilya.bsuirschaduleapp.reafactor.favoriteGroups.domain

import com.ilya.bsuirschaduleapp.reafactor.core.Dispatchers
import com.ilya.bsuirschaduleapp.reafactor.core.HandleError
import com.ilya.bsuirschaduleapp.reafactor.core.Mapper
import com.ilya.bsuirschaduleapp.reafactor.core.UiErrorHandler
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.data.FavoriteRepository
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.domain.FavoriteTeachersInteractor
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.domain.FavoritesInteractor
import com.ilya.bsuirschaduleapp.reafactor.groupList.domain.GroupListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.groupList.presentation.GroupListItemUi
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.TeacherListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListItemUi

/**
 * Created by HP on 28.10.2022.
 **/
interface FavoriteGroupInteractor: FavoritesInteractor<GroupListItemDomain, GroupListItemUi> {
    class Base(
        private val repository: FavoriteRepository<GroupListItemDomain>,
        @UiErrorHandler
        private val handleError: HandleError,
        dispatchers: Dispatchers,
        private val mapper: Mapper<List<GroupListItemDomain>, List<GroupListItemUi>>,
    ): FavoritesInteractor.Abstract<GroupListItemDomain, GroupListItemUi>(
        repository,
        handleError,
        dispatchers,
        mapper
    ), FavoriteGroupInteractor
}