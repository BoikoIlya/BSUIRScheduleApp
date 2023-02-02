package com.ilya.bsuirschaduleapp.reafactor.currweek.di

import com.ilya.bsuirschaduleapp.reafactor.core.PreferenceDataStoreString
import com.ilya.bsuirschaduleapp.reafactor.currweek.data.CurrentWeekRepository
import com.ilya.bsuirschaduleapp.reafactor.currweek.data.cache.CurrentWeekCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.currweek.data.cloud.CurrentWeekCloudDataSource
import com.ilya.bsuirschaduleapp.reafactor.currweek.data.cloud.CurrentWeekService
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cloud.ScheduleService
import com.ilya.bsuirschaduleapp.reafactor.schadule.presentation.CurrentWeekCommunication
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
class CurrentWeekModule {

    @Provides
    @Singleton
    fun provideCurrentWeekCommunication(): CurrentWeekCommunication{
        return CurrentWeekCommunication.Base()
    }

    @Provides
    @Singleton
    fun provideCurrentWeekService(okHttpClient: OkHttpClient): CurrentWeekService
      {
          return Retrofit.Builder()
                .baseUrl(Constance.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(CurrentWeekService::class.java)
        }

    @Provides
    @Singleton
    fun provideCurrentWeekCloudDataSource(
        service: CurrentWeekService
    ): CurrentWeekCloudDataSource {
        return CurrentWeekCloudDataSource.Base(service)
    }

    @Provides
    @Singleton
    fun provideCurrentWeekCacheDataSource(
        cache: PreferenceDataStoreString
    ): CurrentWeekCacheDataSource {
        return CurrentWeekCacheDataSource.Base(cache)
    }

    @Provides
    @Singleton
    fun provideCurrentWeekRepository(
        cache: CurrentWeekCacheDataSource,
        cloud: CurrentWeekCloudDataSource
    ): CurrentWeekRepository {
        return CurrentWeekRepository.Base(cache, cloud)
    }
}