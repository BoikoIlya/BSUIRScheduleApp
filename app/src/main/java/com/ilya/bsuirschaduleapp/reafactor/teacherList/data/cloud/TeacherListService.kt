package com.ilya.bsuirschaduleapp.reafactor.teacherList.data.teacherList.cloud


import com.ilya.bsuirschaduleapp.utils.Constance
import retrofit2.http.GET

/**
 * Created by HP on 17.09.2022.
 **/
interface TeacherListService {

    @GET(Constance.TEACHER_LIST_DESTINATION_URL)
    suspend fun getListOfTeachers(): List<TeacherCloud.Base>
}