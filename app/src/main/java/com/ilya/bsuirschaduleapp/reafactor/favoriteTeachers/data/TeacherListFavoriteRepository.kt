package com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.data

import com.ilya.bsuirschaduleapp.reafactor.core.ListCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.TeacherListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListItemUi

/**
 * Created by HP on 07.10.2022.
 **/
interface TeacherListFavoriteRepository: FavoriteRepository<TeacherListItemDomain> {

    class Base(
        cacheDataSource: ListCacheDataSource<TeacherListItemDomain>,
    ) : FavoriteRepository.Abstract<TeacherListItemDomain>
        (cacheDataSource), TeacherListFavoriteRepository
}
