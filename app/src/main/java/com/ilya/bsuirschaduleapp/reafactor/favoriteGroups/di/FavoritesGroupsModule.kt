package com.ilya.bsuirschaduleapp.reafactor.favoriteGroups.di

import com.ilya.bsuirschaduleapp.reafactor.GroupList.data.cache.GroupListCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.GroupList.domain.GroupListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.GroupList.presentation.GroupListItemUi
import com.ilya.bsuirschaduleapp.reafactor.core.ListCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.favoriteGroups.data.GroupListFavoriteRepository
import com.ilya.bsuirschaduleapp.reafactor.favoriteGroups.data.ToFavoriteGroupListUi
import com.ilya.bsuirschaduleapp.reafactor.favoriteGroups.presentation.FavoriteGroupsCommunication
import com.ilya.bsuirschaduleapp.reafactor.favoriteGroups.presentation.UpdateFavoritesGroups
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.data.FavoriteRepository
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.data.TeacherListFavoriteRepository
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.data.ToFavoriteTeacherListUi
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.TeacherListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListItemUi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by HP on 10.10.2022.
 **/
@Module
@InstallIn(SingletonComponent::class)
class FavoritesGroupsModule {

    @Provides
    @Singleton
    fun provideFavoriteGroupsCommunication(): FavoriteGroupsCommunication{
        return  FavoriteGroupsCommunication.Base(emptyList())
    }

    @Provides
    @Singleton
    fun provideUpdateFavoritesTeachersMutable(): UpdateFavoritesGroups.Mutable{
        return  UpdateFavoritesGroups.Base()
    }

    @Provides
    @Singleton
    fun provideUpdateFavoritesTeachersUpdate(
        updateFavoritesGroups: UpdateFavoritesGroups.Mutable
    ): UpdateFavoritesGroups.Update{
        return  updateFavoritesGroups
    }

    @Provides
    @Singleton
    fun provideFavoriteRepository(
        cacheDataSource: GroupListCacheDataSource,
        mapper: ToFavoriteGroupListUi,
        ): FavoriteRepository<GroupListItemUi> {
        return GroupListFavoriteRepository.Base(cacheDataSource, mapper)
    }
}