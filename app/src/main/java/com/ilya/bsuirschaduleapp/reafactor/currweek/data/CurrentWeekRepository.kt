package com.ilya.bsuirschaduleapp.reafactor.currweek.data

import com.ilya.bsuirschaduleapp.reafactor.core.FetchDataRepository
import com.ilya.bsuirschaduleapp.reafactor.currweek.data.cache.CurrentWeekCacheDataSource
import com.ilya.bsuirschaduleapp.reafactor.currweek.data.cloud.CurrentWeekCloudDataSource

/**
 * Created by HP on 01.11.2022.
 **/
interface CurrentWeekRepository: FetchDataRepository<String> {

    class Base(
        private val cache: CurrentWeekCacheDataSource,
        private val cloud: CurrentWeekCloudDataSource
    ): CurrentWeekRepository{

        override suspend fun fetchData(): String {
               return try{
                    val cloudData = cloud.currentWeek()
                    if(cloudData.isNotEmpty()){
                    cache.save(cloudData)
                    cloudData }
                   else cache.read()
                }catch (e:Exception){
                    return cache.read()
                }
        }
    }
}