package com.ilya.bsuirschaduleapp.reafactor.groupList.data.cloud

import com.ilya.bsuirschaduleapp.reafactor.groupList.domain.GroupListItemDomain

/**
 * Created by HP on 08.10.2022.
 **/
interface GroupCloud {

    fun <T>map(mapper: Mapper<T>):T

    fun get(): Base

    data class Base(
        val calendarId: String,
        val course: Int,
        val facultyId: Int,
        val facultyName: String?,
        val id: Int,
        val name: String,
        val specialityDepartmentEducationFormId: Int,
        val specialityName: String
    ): GroupCloud {

        override  fun <T>map(mapper: Mapper<T>): T {
            return mapper.map(
                course,
                facultyName?:"",
                name,
                specialityName
            )
        }

        override fun get(): Base = this
    }

    interface Mapper<T> {

        fun map(
            course: Int,
            facultyName: String,
            name: String,
            specialityName: String
        ): T

        class Base: Mapper<GroupListItemDomain> {

            override fun map(
                course: Int,
                facultyName: String,
                name: String,
                specialityName: String
            ): GroupListItemDomain {
                return GroupListItemDomain.Base(
                    course = course,
                    facultyName = facultyName,
                    name = name,
                    specialityName = specialityName
                )
            }
        }
    }
}