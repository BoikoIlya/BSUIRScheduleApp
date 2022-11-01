package com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.domain

import com.ilya.bsuirschaduleapp.reafactor.core.Dispatchers
import com.ilya.bsuirschaduleapp.reafactor.core.HandleError
import com.ilya.bsuirschaduleapp.reafactor.core.Mapper
import com.ilya.bsuirschaduleapp.reafactor.core.UiErrorHandler
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.data.FavoriteRepository
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.TeacherListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListItemUi

/**
 * Created by HP on 28.10.2022.
 **/
interface FavoriteTeachersInteractor: FavoritesInteractor<TeacherListItemDomain, TeacherListItemUi>{
    class Base(
        private val repository: FavoriteRepository<TeacherListItemDomain>,
        @UiErrorHandler
        private val handleError: HandleError,
        dispatchers: Dispatchers,
        private val mapper: Mapper<List<TeacherListItemDomain>, List<TeacherListItemUi>>,
    ): FavoritesInteractor.Abstract<TeacherListItemDomain, TeacherListItemUi>(
        repository,
        handleError,
        dispatchers,
        mapper
    ), FavoriteTeachersInteractor
}