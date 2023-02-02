package com.ilya.bsuirschaduleapp.reafactor.currweek.data.cloud

import com.ilya.bsuirschaduleapp.utils.Constance
import retrofit2.http.GET

/**
 * Created by HP on 01.11.2022.
 **/
interface CurrentWeekService {
    @GET(Constance.CURRENT_WEEK_DESTINATION_URL)
    suspend fun getCurrentWeek():Int
}