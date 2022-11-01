package com.ilya.bsuirschaduleapp.reafactor.lastscheduleupdate.di

import com.ilya.bsuirschaduleapp.reafactor.core.DomainErrorHandler
import com.ilya.bsuirschaduleapp.reafactor.core.HandleError
import com.ilya.bsuirschaduleapp.reafactor.core.PreferenceDataStoreString
import com.ilya.bsuirschaduleapp.reafactor.currweek.data.cloud.CurrentWeekService
import com.ilya.bsuirschaduleapp.reafactor.groupList.data.cache.SelectedScheduleIdDataSource
import com.ilya.bsuirschaduleapp.reafactor.lastscheduleupdate.data.ScheduleUpdateDateRepository
import com.ilya.bsuirschaduleapp.reafactor.lastscheduleupdate.data.cache.ScheduleUpdateDateCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.lastscheduleupdate.data.cloud.ScheduleUpdateDateCloudDataSource
import com.ilya.bsuirschaduleapp.reafactor.lastscheduleupdate.data.cloud.ScheduleUpdateDateService
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.SelectedScheduleIdRepository
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
 * Created by HP on 01.11.2022.
 **/

@Module
@InstallIn(SingletonComponent::class)
class ScheduleUpdateDateModule {

    @Provides
    @Singleton
    fun provideScheduleUpdateService(
        okHttpClient:OkHttpClient
    ): ScheduleUpdateDateService{
        return Retrofit.Builder()
            .baseUrl(Constance.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ScheduleUpdateDateService::class.java)
    }

    @Provides
    @Singleton
    fun provideScheduleUpdateCloudDataSource(
        service: ScheduleUpdateDateService,
        @DomainErrorHandler
        handleError: HandleError
    ): ScheduleUpdateDateCloudDataSource{
        return ScheduleUpdateDateCloudDataSource.Base(service, handleError)
    }

    @Provides
    @Singleton
    fun provideScheduleUpdateCacheDataSource(
        cache: PreferenceDataStoreString
    ): ScheduleUpdateDateCacheDataSource {
        return ScheduleUpdateDateCacheDataSource.Base(cache)
    }

    @Provides
    @Singleton
    fun provideScheduleUpdateRepository(
        cache: ScheduleUpdateDateCacheDataSource,
        cloud: ScheduleUpdateDateCloudDataSource,
        cachedId: SelectedScheduleIdDataSource
    ): ScheduleUpdateDateRepository {
        return ScheduleUpdateDateRepository.Base(cache, cloud, cachedId)
    }

}