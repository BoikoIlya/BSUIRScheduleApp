package com.ilya.bsuirschaduleapp.reafactor.groupList.data.cloud

import com.ilya.bsuirschaduleapp.utils.Constance
import retrofit2.http.GET

/**
 * Created by HP on 08.10.2022.
 **/
interface GroupListService {

    @GET(Constance.GROUP_LIST_DESTINATION_URL)
    suspend fun getListOfGroups(): List<GroupCloud.Base>
}