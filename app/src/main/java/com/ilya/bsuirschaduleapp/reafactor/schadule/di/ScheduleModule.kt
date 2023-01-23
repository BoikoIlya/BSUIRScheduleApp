package com.ilya.bsuirschaduleapp.reafactor.schadule.di

import android.content.Context
import android.content.SharedPreferences
import androidx.work.WorkManager
import com.ilya.bsuirschaduleapp.reafactor.core.*
import com.ilya.bsuirschaduleapp.reafactor.dailyupdate.WorkManagerWrapper
import com.ilya.bsuirschaduleapp.reafactor.groupList.data.cache.SelectedScheduleIdDataSource
import com.ilya.bsuirschaduleapp.reafactor.lastscheduleupdate.data.ScheduleUpdateDateRepository
import com.ilya.bsuirschaduleapp.reafactor.lastscheduleupdate.data.cache.ScheduleUpdateDateCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.*
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cache.*
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cloud.*
import com.ilya.bsuirschaduleapp.reafactor.schadule.domain.ScheduleDomain
import com.ilya.bsuirschaduleapp.reafactor.schadule.domain.ScheduleDomainToUiMapper
import com.ilya.bsuirschaduleapp.reafactor.schadule.domain.ScheduleInteractor
import com.ilya.bsuirschaduleapp.reafactor.schadule.presentation.ScheduleCommunication
import com.ilya.bsuirschaduleapp.reafactor.schadule.presentation.ScheduleProgressCommunication
import com.ilya.bsuirschaduleapp.reafactor.schadule.presentation.SubGroupCommunication
import com.ilya.bsuirschaduleapp.utils.Constance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by HP on 26.10.2022.
 **/
@Module
@InstallIn(SingletonComponent::class)
class ScheduleModule {

    @Provides
    @Singleton
    fun provideSelectedScheduleNameDataSource(
        cache: PreferenceDataStoreString
    ): SelectedScheduleNameDataSource{
        return SelectedScheduleNameDataSource.Base(cache)
    }

    @Provides
    @Singleton
    fun provideSelectedScheduleNameDataRepository(
        cache: SelectedScheduleNameDataSource
    ): SelectedScheduleNameRepository {
        return SelectedScheduleNameRepository.Base(cache)
    }

    @Provides
    @Singleton
    fun provideToSelectedScheduleName(): ScheduleDomain.Mapper.ToSelectedScheduleName {
        return ScheduleDomain.Mapper.ToSelectedScheduleName()
    }

    @Provides
    @Singleton
    fun provideToSelectedScheduleNameMapper(
        mapper: ScheduleDomain.Mapper.ToSelectedScheduleName
    ): ToSelectedScheduleNameMapper {
        return ToSelectedScheduleNameMapper.Base(mapper)
    }

    @Singleton
    @Provides
    fun provideSubGroupCommunication(): SubGroupCommunication{
        return SubGroupCommunication.Base()
    }

    @Singleton
    @Provides
    fun providesSelectedScheduleIdRepository(
        cache: SelectedScheduleIdDataSource
    ): SelectedScheduleIdRepository {
        return SelectedScheduleIdRepository.Base(cache)
    }

    @Singleton
    @Provides
    fun providePreferenciesDataStore(
        sharedPreferences: SharedPreferences
    ): PreferenceDataStoreString{
        return PreferenceDataStoreString(sharedPreferences)
    }

    @Singleton
    @Provides
    fun provideScheduleCacheDataSource(
        scheduleDao: ScheduleDao,
        mapper: ScheduleDomainToCacheMapper
    ): ScheduleCacheDataSource {
        return ScheduleCacheDataSource.Base(scheduleDao,mapper)
    }

    @Singleton
    @Provides
    fun provideScheduleDomainToCacheMapper(
        mapper: ScheduleDomain.Mapper.ToScheduleCache
    ): ScheduleDomainToCacheMapper {
        return ScheduleDomainToCacheMapper.Base(mapper)
    }

    @Singleton
    @Provides
    fun provideToScheduleCache(): ScheduleDomain.Mapper.ToScheduleCache {
        return ScheduleDomain.Mapper.ToScheduleCache()
    }


    @Singleton
    @Provides
    fun provideScheduleCacheToDomain(
        cachedId: SelectedScheduleIdRepository,
        cachedName: SelectedScheduleNameRepository
    ): ScheduleCacheToDomainMapper{
        return ScheduleCacheToDomainMapper.Base(cachedId,cachedName)
    }

    @Singleton
    @Provides
    fun provideSelectedScheduleDataSource(
        cache: PreferenceDataStoreString
    ): SelectedScheduleIdDataSource{
        return SelectedScheduleIdDataSource.Base(cache)
    }

    @Singleton
    @Provides
    fun provideToSelectedScheduleMapper(
        mapper: ScheduleDomain.Mapper.ToSelectedScheduleId
    ): ToSelectedScheduleIdMapper {
        return ToSelectedScheduleIdMapper.Base(mapper)
    }

    @Singleton
    @Provides
    fun provideToSelectedSchedule():  ScheduleDomain.Mapper.ToSelectedScheduleId {
        return ScheduleDomain.Mapper.ToSelectedScheduleId()
    }

    @Singleton
    @Provides
    fun provideCloudScheduleToDomainMapper(
        mapper: ScheduleCloud.Mapper<ScheduleDomain>
    ): CloudScheduleToDomainMapper {
        return CloudScheduleToDomainMapper.Base(mapper)
    }

    @Singleton
    @Provides
    fun provideCloudScheduleToDomain(): ScheduleCloud.Mapper<ScheduleDomain>{
        return ScheduleCloud.Mapper.Base()
    }

    @Singleton
    @Provides
    fun provideScheduleService(
        okHttpClient: OkHttpClient
    ): ScheduleService{
        return Retrofit.Builder()
            .baseUrl(Constance.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ScheduleService::class.java)
    }

    @Singleton
    @Provides
    fun provideScheduleTeacherCloudDataSource(
        scheduleService: ScheduleService,
        @DomainErrorHandler
        handleError: HandleError
    ): ScheduleTeacherCloudDataSource {
        return ScheduleTeacherCloudDataSource.Base(scheduleService,handleError)
    }

    @Singleton
    @Provides
    fun provideScheduleGroupCloudDataSource(
        scheduleService: ScheduleService,
        @DomainErrorHandler
        handleError: HandleError
    ): ScheduleGroupCloudDataSource {
        return ScheduleGroupCloudDataSource.Base(scheduleService,handleError)
    }

    @Singleton
    @Provides
    fun provideScheduleRepository(
        cloudToDomainMapper: CloudScheduleToDomainMapper,
        cacheToDomainMapper: ScheduleCacheToDomainMapper,
        toSelectedScheduleIdMapper: ToSelectedScheduleIdMapper,
        cloudTeacherSchedule: ScheduleTeacherCloudDataSource,
        cloudGroupSchedule: ScheduleGroupCloudDataSource,
        scheduleCacheDataSource: ScheduleCacheDataSource,
        selectedScheduleIdDataSource: SelectedScheduleIdDataSource,
        selectedScheduleNameDataSource: SelectedScheduleNameDataSource,
        toSelectedScheduleNameMapper: ToSelectedScheduleNameMapper,
        scheduleUpdateDateCacheDataSource: ScheduleUpdateDateCacheDataSource
    ): ScheduleRepository<ScheduleCache, ScheduleDomain> {
        return BaseScheduleRepository(
           cloudToDomainMapper = cloudToDomainMapper,
           cacheToDomainMapper = cacheToDomainMapper,
           toSelectedScheduleIdMapper =  toSelectedScheduleIdMapper,
           cloudTeacherSchedule =  cloudTeacherSchedule,
           cloudGroupSchedule = cloudGroupSchedule,
           scheduleCacheDataSource =  scheduleCacheDataSource,
           selectedScheduleIdDataSource = selectedScheduleIdDataSource,
            selectedScheduleNameDataSource = selectedScheduleNameDataSource,
            toSelectedScheduleNameMapper = toSelectedScheduleNameMapper,
            scheduleUpdateDateCacheDataSource = scheduleUpdateDateCacheDataSource
         )
    }

    @Singleton
    @Provides
    fun provideSubGroupRepositoryRead(
      cache: SubGroupRepository.Mutable
    ): SubGroupRepository.Read {
        return cache
    }

    @Singleton
    @Provides
    fun provideSubGroupRepositorySave(
        cache: SubGroupRepository.Mutable
    ): SubGroupRepository.Save {
        return cache
    }

    @Singleton
    @Provides
    fun provideSubGroupRepositoryMutable(
        cache: PreferenceDataStoreString
    ): SubGroupRepository.Mutable {
        return SubGroupRepository.Base(cache)
    }

    @Singleton
    @Provides
    fun provideScheduleDomainToUiMapper(
        mapper: ScheduleDomain.Mapper.ToScheduleUi
    ): ScheduleDomainToUiMapper {
        return ScheduleDomainToUiMapper.Base(mapper)
    }

    @Singleton
    @Provides
    fun provideToScheduleUi(
        subGroupRepository: SubGroupRepository.Read
    ): ScheduleDomain.Mapper.ToScheduleUi {
        return ScheduleDomain.Mapper.ToScheduleUi(subGroupRepository)
    }

    @Singleton
    @Provides
    fun provideScheduleInteractor(
        @UiErrorHandler
        handleError: HandleError,
        dispatchers: Dispatchers,
        scheduleRepository: ScheduleRepository<ScheduleCache, ScheduleDomain>,
        mapper: ScheduleDomainToUiMapper,
        scheduleUpdateDateRepository: ScheduleUpdateDateRepository
    ): ScheduleInteractor{
        return ScheduleInteractor.BaseScheduleInteractor(
            handleError,
            dispatchers,
            scheduleRepository,
            mapper,
            scheduleUpdateDateRepository = scheduleUpdateDateRepository
        )
    }

    @Singleton
    @Provides
    fun provideScheduleCommunication(): ScheduleCommunication{
        return ScheduleCommunication.Base()
    }

    @Singleton
    @Provides
    fun provideScheduleProgressCommunication(): ScheduleProgressCommunication{
        return ScheduleProgressCommunication.Base()
    }

    @Singleton
    @Provides
    fun provideWorkManagerWrapper(@ApplicationContext context: Context): WorkManagerWrapper{
        return WorkManagerWrapper.Base(context)
    }
}