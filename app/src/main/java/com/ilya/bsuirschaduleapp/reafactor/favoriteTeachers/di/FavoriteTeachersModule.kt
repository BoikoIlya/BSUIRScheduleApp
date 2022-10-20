package com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.di

import com.ilya.bsuirschaduleapp.reafactor.core.ListCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.data.FavoriteRepository
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.data.TeacherListFavoriteRepository
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.data.ToFavoriteTeacherListUi
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.presentation.FavoriteTeachersCommunication
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.presentation.UpdateFavoritesTeachers
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.TeacherListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListItemUi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by HP on 02.10.2022.
 **/
@Module
@InstallIn(SingletonComponent::class)
class FavoriteTeachersModule {

    @Provides
    @Singleton
     fun provideFavoriteRepository(
        cacheDataSource: ListCacheDataSource<TeacherListItemDomain>,
        mapper:  ToFavoriteTeacherListUi,

        ): FavoriteRepository<TeacherListItemUi>{
         return TeacherListFavoriteRepository.Base(cacheDataSource, mapper)
    }

    @Provides
    @Singleton
     fun provideFavoriteTeacherListCommunication(): FavoriteTeachersCommunication{
        return FavoriteTeachersCommunication.Base(emptyList())
    }

    @Provides
    @Singleton
    fun provideUpdateCommunication(): UpdateFavoritesTeachers.Mutable{
        return UpdateFavoritesTeachers.Base()
    }

    @Provides
    @Singleton
    fun provideUpdateCommunicationCollect(
        updateFavorites: UpdateFavoritesTeachers.Mutable
    ): UpdateFavoritesTeachers.Update{
        return updateFavorites
    }
   /* @Provides
    @Singleton
    fun provideCollectCommunicationCollect(
        updateFavorites: UpdateFavorites.Mutable
    ): UpdateFavorites.Collect{
        return updateFavorites
    }*/
}