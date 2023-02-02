package com.ilya.bsuirschaduleapp.reafactor.main.di

import android.content.Context
import androidx.room.Room
import com.ilya.bsuirschaduleapp.reafactor.core.*
import com.ilya.bsuirschaduleapp.reafactor.main.data.ThemeCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.main.data.ThemeRepository
import com.ilya.bsuirschaduleapp.reafactor.main.presentation.ThemeCommunication
import com.ilya.bsuirschaduleapp.reafactor.teacherList.data.teacherList.cloud.TeacherListService
import com.ilya.bsuirschaduleapp.utils.Constance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by HP on 04.10.2022.
 **/
@Module
@InstallIn(SingletonComponent::class)
class MainModule {


    @Provides
    @Singleton
    fun provideThemeCacheDataSource(
       cache: PreferenceDataStoreString
    ): ThemeCacheDataSource {
        return ThemeCacheDataSource.Base(cache)
    }

    @Provides
    @Singleton
    fun provideThemeRepository(
        cache: ThemeCacheDataSource
    ): ThemeRepository {
        return ThemeRepository.Base(cache)
    }

    @Provides
    @Singleton
    fun provideThemeCommunication(): ThemeCommunication {
        return ThemeCommunication.Base()
    }


    @DomainErrorHandler
    @Provides
    @Singleton
    fun provideHandleDomainError(): HandleError {
        return HandleDomainError()
    }

    @Provides
    @Singleton
    fun provideResourceManager(
        @ApplicationContext
        context: Context
    ): ManageResource {
        return ManageResource.Base(context)
    }


    @Provides
    @Singleton
    fun provideGlobalErrorCommunicationMutable(): GlobalErrorCommunication.Mutable{
        return GlobalErrorCommunication.Base()
    }

    @Provides
    @Singleton
    fun provideGlobalErrorCommunicationUpdate(
        globalErrorCommunication: GlobalErrorCommunication.Mutable
    ): GlobalErrorCommunication.Update{
        return globalErrorCommunication
    }

    @Provides
    @Singleton
    @UiErrorHandler
    fun provideHandleUiError(
        resource: ManageResource,
        globalErrorCommunication: GlobalErrorCommunication.Mutable,
    ): HandleError{
        return HandleUiError(resource,globalErrorCommunication)
    }

    @Provides
    @Singleton
    fun provideDispatchers(): Dispatchers {
        return Dispatchers.Base()
    }

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        interceptor: Interceptor
    ): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideTeacherListService(
        okHttpClient: OkHttpClient
    ): TeacherListService {
        return Retrofit.Builder()
            .baseUrl(Constance.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(TeacherListService::class.java)
    }

    @Provides
    @Singleton
    fun provideScheduleDB(
        @ApplicationContext
        context: Context
    ): ScheduleDB {
        return Room.databaseBuilder(
            context,
            ScheduleDB::class.java,
            "my_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideScheduleDao(
        scheduleDB: ScheduleDB
    ): ScheduleDao {
        return scheduleDB.getDao()
    }
}