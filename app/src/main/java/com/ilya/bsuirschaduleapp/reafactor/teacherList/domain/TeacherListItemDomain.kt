package com.ilya.bsuirschaduleapp.reafactor.teacherList.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ilya.bsuirschaduleapp.reafactor.core.IsFavorite
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListItemUi

/**
 * Created by HP on 17.09.2022.
 **/
interface TeacherListItemDomain {

    fun <T>map(mapper: Mapper<T>):T

    data class Base(
        val fio: String,
        val academicDepartment: String,
        val firstName: String,
        val lastName: String,
        val middleName: String,
        val photoLink: String,
        val rank: String,
        val urlId: String
    ): TeacherListItemDomain {

        override  fun<T> map(mapper: Mapper<T>): T {
         return mapper.map(
             fio,
             academicDepartment,
             firstName,
             lastName,
             middleName,
             photoLink,
             rank,
             urlId
         )
        }
    }

    interface Mapper<T>{

        fun map(
            fio: String,
            academicDepartment: String,
            firstName: String,
            lastName: String,
            middleName: String,
            photoLink: String,
            rank: String,
            urlId: String
        ):T

        class ToTeacherItemUi(
            private val isFavorite: IsFavorite
        ): Mapper<TeacherListItemUi> {

            override fun map(
                fio: String,
                academicDepartment: String,
                firstName: String,
                lastName: String,
                middleName: String,
                photoLink: String,
                rank: String,
                urlId: String
            ): TeacherListItemUi {
                return TeacherListItemUi(
                    fio =  fio,
                    academicDepartment= academicDepartment,
                    fullFIO = "$lastName $firstName $middleName",
                    photoLink = photoLink,
                    rank = rank,
                    urlId = urlId,
                    isFavorite = isFavorite.isFavorite(urlId)
                )
            }
        }
    }
}


