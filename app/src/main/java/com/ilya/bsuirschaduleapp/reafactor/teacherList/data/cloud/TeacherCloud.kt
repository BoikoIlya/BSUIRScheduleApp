package com.ilya.bsuirschaduleapp.reafactor.teacherList.data.teacherList.cloud

import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.TeacherListItemDomain

/**
 * Created by HP on 17.09.2022.
 **/
interface TeacherCloud {

     fun <T>map(mapper: Mapper<T>):T

     fun get(): Base

    data class Base(
        val academicDepartment: List<String>,
        val calendarId: String?="",
        val degree: String,
        val fio: String,
        val firstName: String,
        val id: Int,
        val lastName: String,
        val middleName: String,
        val photoLink: String,
        val rank: String?="",
        val urlId: String
    ):TeacherCloud{
        override  fun <T>map(mapper: Mapper<T>): T {
          return mapper.map(
              academicDepartment,
            fio,
            firstName,
            lastName,
            middleName,
            photoLink,
            rank,
            urlId
          )
        }

        override fun get(): Base = this
    }

    interface Mapper<T> {

        fun map(
            academicDepartment: List<String>,
            fio: String,
            firstName: String,
            lastName: String,
            middleName: String,
            photoLink: String,
            rank: String?,
            urlId: String
        ): T

        class Base: Mapper<TeacherListItemDomain> {

            override fun map(
                academicDepartment: List<String>,
                fio: String,
                firstName: String,
                lastName: String,
                middleName: String,
                photoLink: String,
                rank: String?,
                urlId: String
            ): TeacherListItemDomain {
                return TeacherListItemDomain.Base(
                    fio = fio,
                    academicDepartment = academicDepartment.joinToString(),
                    firstName = firstName,
                    lastName = lastName,
                    middleName = middleName,
                    photoLink = photoLink,
                    rank = rank?:"",
                    urlId = urlId
                )
            }
        }
    }
}

