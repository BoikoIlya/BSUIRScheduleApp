package com.ilya.bsuirschaduleapp.reafactor.schadule.data.cloud

import com.ilya.bsuirschaduleapp.data.network.dto.ScheduleResponseDto
import com.ilya.bsuirschaduleapp.utils.Constance
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by HP on 25.10.2022.
 **/
interface ScheduleService {

    @GET(Constance.GROUP_SCHEDULE_DESTINATION_URL)
    suspend fun getScheduleByGroupNumber(
        @Query("studentGroup")
        studentGroup: String
    ): ScheduleCloud.Base

    @GET(Constance.TEACHER_SCHEDULE_DESTINATION_URL)
    suspend fun getScheduleByTeacherUrlId(
        @Path("urlId")
        urlId: String
    ): ScheduleCloud.Base
}