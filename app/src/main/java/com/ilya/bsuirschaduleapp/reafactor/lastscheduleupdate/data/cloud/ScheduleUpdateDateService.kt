package com.ilya.bsuirschaduleapp.reafactor.lastscheduleupdate.data.cloud

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by HP on 01.11.2022.
 **/
interface ScheduleUpdateDateService {

    @GET("/api/v1/last-update-date/student-group")
    suspend fun getLastGroupScheduleUpdate(
        @Query("groupNumber")
        groupNumber: String
    ):LastUpdateDate


    @GET("/api/v1/last-update-date/employee")
    suspend fun getLastTeacherScheduleUpdate(
        @Query("url-id")
        urlId: String
    ):LastUpdateDate
}