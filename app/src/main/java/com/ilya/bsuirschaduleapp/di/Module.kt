package com.ilya.bsuirschaduleapp.di

import android.content.Context
import androidx.room.Room
import com.ilya.bsuirschaduleapp.data.local.db.ScheduleDB
import com.ilya.bsuirschaduleapp.data.local.db.ScheduleDao
import com.ilya.bsuirschaduleapp.data.local.datastore.DataStore
import com.ilya.bsuirschaduleapp.data.local.datastore.DataStoreImpl
import com.ilya.bsuirschaduleapp.data.network.api.ScheduleApi
import com.ilya.bsuirschaduleapp.data.repositories.DataBaseRepositoryImpl
import com.ilya.bsuirschaduleapp.data.repositories.DataStoreRepositoryImpl
import com.ilya.bsuirschaduleapp.data.repositories.NetworkRepositoryImpl
import com.ilya.bsuirschaduleapp.domain.repositiries.DataBaseRepository
import com.ilya.bsuirschaduleapp.domain.repositiries.DataStoreRepository
import com.ilya.bsuirschaduleapp.domain.repositiries.NetworkRepository
import com.ilya.bsuirschaduleapp.domain.usecases.*
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

@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor{
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
    fun provideScheduleApi(
        okHttpClient: OkHttpClient
    ): ScheduleApi{
        return Retrofit.Builder()
            .baseUrl(Constance.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ScheduleApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkRepository(
        scheduleApi: ScheduleApi
    ): NetworkRepository{
        return NetworkRepositoryImpl(scheduleApi)
    }

    @Provides
    @Singleton
    fun provideGetScheduleByGroupNumberUseCase(
        networkRepository: NetworkRepository
    ): GetScheduleByGroupNumberUseCase{
        return GetScheduleByGroupNumberUseCase(networkRepository)
    }

    @Provides
    @Singleton
    fun provideGetCurrentWeekUseCase(
        networkRepository: NetworkRepository
    ): GetCurrentWeekUseCase {
        return GetCurrentWeekUseCase(networkRepository)
    }

    @Provides
    @Singleton
    fun provideGetScheduleByTeacherUrlIdUseCase(
        networkRepository: NetworkRepository
    ): GetScheduleByTeacherUrlIdUseCase {
        return GetScheduleByTeacherUrlIdUseCase(networkRepository)
    }

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext
        context: Context
    ): DataStore {
        return DataStoreImpl(context)
    }

    @Provides
    @Singleton
    fun provideGetListOfGroupsUseCase(
        networkRepository: NetworkRepository
    ): GetListOfGroupsUseCase{
        return GetListOfGroupsUseCase(networkRepository)
    }

    @Provides
    @Singleton
    fun provideGetListOfTeachersUseCase(
        networkRepository: NetworkRepository
    ): GetListOfTeachersUseCase {
        return GetListOfTeachersUseCase(networkRepository)
    }

    @Provides
    @Singleton
    fun provideScheduleDB(
        @ApplicationContext
        context: Context
    ): ScheduleDB{
        return Room.databaseBuilder(
            context,
            ScheduleDB::class.java,
            Constance.SCHEDULE_DB
        ).build()
    }

    @Provides
    @Singleton
    fun provideScheduleDao(
       scheduleDB: ScheduleDB
    ): ScheduleDao{
        return scheduleDB.getDao()
    }

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        dataStore: DataStore
    ): DataStoreRepository{
        return DataStoreRepositoryImpl(dataStore)
    }

    @Provides
    @Singleton
    fun provideDataBaseRepository(
        scheduleDao: ScheduleDao
    ): DataBaseRepository {
        return DataBaseRepositoryImpl(scheduleDao)
    }

}