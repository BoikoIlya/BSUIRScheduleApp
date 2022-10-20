package com.ilya.bsuirschaduleapp.reafactor.teacherList.di

import android.content.Context
import android.content.SharedPreferences
import com.ilya.bsuirschaduleapp.reafactor.core.*
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.data.ToFavoriteTeacherListUi
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.data.ToTeacherListItemUi
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.ListInteractor
import com.ilya.bsuirschaduleapp.reafactor.teacherList.data.BaseTeacherListRepository
import com.ilya.bsuirschaduleapp.reafactor.teacherList.data.cache.BaseTeacherListCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.data.FavoritesTeachersCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.teacherList.data.cloud.TeacherListCloudDataSource
import com.ilya.bsuirschaduleapp.reafactor.teacherList.data.cloud.ToTeacherListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.teacherList.data.teacherList.cloud.TeacherCloud
import com.ilya.bsuirschaduleapp.reafactor.teacherList.data.teacherList.cloud.TeacherListService
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.BaseTeacherListInteractor
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListProgressCommunication
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListCommunication
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.TeacherListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListItemUi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by HP on 17.09.2022.
 **/
@Module
@InstallIn(SingletonComponent::class)
class TeacherListModule {

    @Provides
    @Singleton
    fun provideChangeFavorite(
        cloudDataSource: TeacherListCloudDataSource,
        mapper: ToTeacherListItemDomain,
        cache: ListCacheDataSource<TeacherListItemDomain>,
        favoriteCacheDataSource: FavoritesTeachersCacheDataSource
    ): ChangeFavorite{
        return BaseTeacherListRepository(cloudDataSource, cache, mapper, favoriteCacheDataSource)
    }


    @Provides
    @Singleton
    fun provideSharedPrefForSetOfFavorites(
        @ApplicationContext
        context: Context
    ): SharedPreferences{
        return  context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferenceDataStoreForSetOfFavorites(
        sharedPreferences: SharedPreferences
    ): PreferenceDataStore<Set<String>>{
        return PreferenceDataStoreSet(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideFavoriteTeacherCacheDataSource(
        favorites: PreferenceDataStore<Set<String>>
    ): FavoritesTeachersCacheDataSource {
       return FavoritesTeachersCacheDataSource.Base("favorite_teachers",favorites)
    }

    @Provides
    @Singleton
    fun provideFavoriteTeacherCacheDataSourceIsFavorite(
        favoriteCacheDataSource: FavoritesTeachersCacheDataSource
    ): IsFavorite{
        return favoriteCacheDataSource
    }

    @Provides
    @Singleton
    fun provideTeacherListDataSource(
        scheduleDao: ScheduleDao,
    ): ListCacheDataSource<TeacherListItemDomain>{
        return BaseTeacherListCacheDataSource(scheduleDao)
    }

    @Provides
    @Singleton
    fun provideRepository(
        cloudDataSource: TeacherListCloudDataSource,
        mapper: ToTeacherListItemDomain,
        cache: ListCacheDataSource<TeacherListItemDomain>,
        favoriteCacheDataSource: FavoritesTeachersCacheDataSource
    ): Repository<List<TeacherListItemDomain>>{
        return BaseTeacherListRepository(cloudDataSource, cache, mapper, favoriteCacheDataSource)
    }

    @Provides
    @Singleton
    fun provideProgressCommunication():TeacherListProgressCommunication{
        return  TeacherListProgressCommunication.Base()
    }


    @Provides
    @Singleton
    fun provideTeacherListInteractor(
        repository: Repository<List<TeacherListItemDomain>>,
        @UiErrorHandler
        handleError: HandleError,
        dispatchers: Dispatchers,
        mapper: ToTeacherListItemUi
    ): ListInteractor<TeacherListItemDomain, TeacherListItemUi> {
        return BaseTeacherListInteractor(repository,handleError, dispatchers,mapper)
    }

    @Provides
    @Singleton
    fun provideTeacherListCommunication(): TeacherListCommunication{
        return TeacherListCommunication.Base(emptyList())
    }
    @Provides
    @Singleton
    fun provideCloudDataSource(
        teacherListService: TeacherListService,
        @DomainErrorHandler
        handleError: HandleError
    ): TeacherListCloudDataSource {
        return TeacherListCloudDataSource.Base(teacherListService, handleError)
    }

    @Provides
    @Singleton
    fun provideCloudDataMapper():TeacherCloud.Mapper<TeacherListItemDomain>{
        return TeacherCloud.Mapper.Base()
    }

    @Provides
    @Singleton
    fun provideTeacherListItemDomainMapper(
        isFavorite: FavoritesTeachersCacheDataSource
    ): TeacherListItemDomain.Mapper<TeacherListItemUi>{
        return TeacherListItemDomain.Mapper.Base(isFavorite)
    }

    @Provides
    @Singleton
    fun provideToTeacherDomainMapper(
        mapper: TeacherCloud.Mapper<TeacherListItemDomain>
    ):ToTeacherListItemDomain{
        return ToTeacherListItemDomain.Base(mapper)
    }

    @Provides
    @Singleton
    fun provideToFavoriteTeacherListItemUiMapper(
        mapper: TeacherListItemDomain.Mapper<TeacherListItemUi>
    ):ToFavoriteTeacherListUi{
        return ToFavoriteTeacherListUi.Base(mapper)
    }

    @Provides
    @Singleton
    fun provideToTeacherListItemUiMapper(
        mapper: TeacherListItemDomain.Mapper<TeacherListItemUi>
    ):ToTeacherListItemUi{
        return ToTeacherListItemUi.Base(mapper)
    }

}