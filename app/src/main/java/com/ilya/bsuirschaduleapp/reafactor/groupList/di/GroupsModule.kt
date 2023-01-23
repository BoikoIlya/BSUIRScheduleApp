package com.ilya.bsuirschaduleapp.reafactor.groupList.di

import com.ilya.bsuirschaduleapp.reafactor.groupList.data.GroupListRepository
import com.ilya.bsuirschaduleapp.reafactor.groupList.data.cache.*
import com.ilya.bsuirschaduleapp.reafactor.groupList.data.cloud.GroupCloud
import com.ilya.bsuirschaduleapp.reafactor.groupList.data.cloud.GroupCloudDataSource
import com.ilya.bsuirschaduleapp.reafactor.groupList.data.cloud.GroupListService
import com.ilya.bsuirschaduleapp.reafactor.groupList.data.cloud.ToGroupListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.groupList.domain.GroupListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.groupList.presentation.GroupListCommunication
import com.ilya.bsuirschaduleapp.reafactor.groupList.presentation.GroupListItemUi
import com.ilya.bsuirschaduleapp.reafactor.groupList.presentation.GroupListProgressCommunication
import com.ilya.bsuirschaduleapp.reafactor.groupList.presentation.ToGroupListItemUi
import com.ilya.bsuirschaduleapp.reafactor.core.*
import com.ilya.bsuirschaduleapp.reafactor.favoriteGroups.data.FavoriteGroupsCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.favoriteGroups.data.ToFavoriteGroupListUi
import com.ilya.bsuirschaduleapp.reafactor.groupList.domain.GroupListInteractor
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.ListInteractor
import com.ilya.bsuirschaduleapp.utils.Constance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by HP on 09.10.2022.
 **/
@Module
@InstallIn(SingletonComponent::class)
class GroupsModule {

    @Provides
    @Singleton
    fun provideGroupListService(
        okHttpClient: OkHttpClient
    ): GroupListService {
        return Retrofit.Builder()
            .baseUrl(Constance.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(GroupListService::class.java)
    }

    @Provides
    @Singleton
    fun provideChangeFavorite(
        cloudDataSource: GroupCloudDataSource,
        mapper: ToGroupListItemDomain,
        cache: GroupListCacheDataSource,
        favoriteCacheDataSource: FavoriteGroupsCacheDataSource
    ): GroupListRepository {                                                            //
        return GroupListRepository.Base(cloudDataSource, cache, mapper, favoriteCacheDataSource)
    }


    @Provides
    @Singleton
    fun provideFavoriteGroupCacheDataSource(
        favorites: PreferenceDataStore<Set<String>>
    ): FavoriteGroupsCacheDataSource {
        return FavoriteGroupsCacheDataSource.Base("favorite_groups",favorites)
    }

    @Provides
    @Singleton
    fun provideFavoriteGroupCacheDataSourceIsFavorite(
        favoriteCacheDataSource: FavoriteGroupCacheDataSource
    ): IsFavorite {
        return favoriteCacheDataSource
    }

    @Provides
    @Singleton
    fun provideTeacherListDataSource(
        scheduleDao: ScheduleDao,
        fromCache: GroupCacheToGroupDomain,
        toCache: ToGroupCacheMapper
    ): GroupListCacheDataSource {
        return GroupListCacheDataSource.Base(scheduleDao,toCache, fromCache)
    }

  @Provides
  @Singleton
  fun provideRepository(
     groupListRepository: GroupListRepository
  ): Repository<List<GroupListItemDomain>> {
      return groupListRepository
  }

    @Provides
    @Singleton
    fun provideGroupProgressCommunication(): GroupListProgressCommunication {
        return  GroupListProgressCommunication.Base()
    }


    @Provides
    @Singleton
    fun provideGroupListInteractor(
        repository: Repository<List<GroupListItemDomain>>,
        @UiErrorHandler
        handleError: HandleError,
        dispatchers: Dispatchers,
        mapper: ToGroupListItemUi
    ): GroupListInteractor {
        return GroupListInteractor.Base(repository,handleError, dispatchers,mapper)
    }

    @Provides
    @Singleton
    fun provideGroupListCommunication(): GroupListCommunication {
        return GroupListCommunication.Base(emptyList())
    }

    @Provides
    @Singleton
    fun provideCloudDataSource(
        groupListService: GroupListService,
        @DomainErrorHandler
        handleError: HandleError
    ): GroupCloudDataSource {
        return GroupCloudDataSource.Base(groupListService, handleError)
    }

    @Provides
    @Singleton
    fun provideCloudDataMapper(): GroupCloud.Mapper<GroupListItemDomain>{
        return GroupCloud.Mapper.Base()
    }

    @Provides
    @Singleton
    fun provideGroupListItemDomainMapper(
        isFavorite: FavoriteGroupsCacheDataSource
    ): GroupListItemDomain.Mapper<GroupListItemUi>{
        return GroupListItemDomain.Mapper.Base(isFavorite)
    }

    @Provides
    @Singleton
    fun provideToGroupDomainMapper(
        mapper: GroupCloud.Mapper<GroupListItemDomain>
    ): ToGroupListItemDomain {
        return ToGroupListItemDomain.Base(mapper)
    }

    @Provides
    @Singleton
    fun provideToFavoriteGroupListItemUiMapper(
        mapper: GroupListItemDomain.Mapper<GroupListItemUi>
    ): ToFavoriteGroupListUi {
        return ToFavoriteGroupListUi.Base(mapper)
    }

    @Provides
    @Singleton
    fun provideToGroupListItemUiMapper(
        mapper: GroupListItemDomain.Mapper<GroupListItemUi>
    ): ToGroupListItemUi {
        return ToGroupListItemUi.Base(mapper)
    }

    @Provides
    @Singleton
    fun provideToGroupCacheMapper(
        mapper: GroupDomainToGroupCache
    ): ToGroupCacheMapper{
        return ToGroupCacheMapper.Base(mapper)
    }

    @Provides
    @Singleton
    fun provideGroupDomainToGroupCache(): GroupDomainToGroupCache{
        return GroupDomainToGroupCache.Base()
    }

    @Provides
    @Singleton
    fun provideGroupCacheToGroupDomain(): GroupCacheToGroupDomain{
        return GroupCacheToGroupDomain.Base()
    }
}