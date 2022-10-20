package com.ilya.bsuirschaduleapp.reafactor.GroupList.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ilya.bsuirschaduleapp.reafactor.GroupList.presentation.GroupListItemUi
import com.ilya.bsuirschaduleapp.reafactor.core.IsFavorite
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.TeacherListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListItemUi

/**
 * Created by HP on 08.10.2022.
 **/
interface GroupListItemDomain {

   fun <T>map(mapper: Mapper<T>):T

   fun get(): Base

   @Entity(tableName = "groups_list")
   data class Base(
      val course: Int,
      val facultyName: String,
      @PrimaryKey(autoGenerate = false)
      val name: String,
      val specialityName: String,
   ): GroupListItemDomain {

      override  fun<T> map(mapper: Mapper<T>): T {
         return mapper.map(
            course,
            facultyName,
            name,
            specialityName,
         )
      }

      override fun get(): Base = this
   }

   interface Mapper<T>{

      fun map(
         course: Int,
         facultyName: String,
         name: String,
         specialityName: String,
      ):T

      class Base(
         private val isFavorite: IsFavorite
      ): Mapper<GroupListItemUi> {

         override fun map(
            course: Int,
            facultyName: String,
            name: String,
            specialityName: String,
         ): GroupListItemUi {
            return  GroupListItemUi(
               course = "Курс: $course",
               facultyName = facultyName,
               name = name,
               specialityName = specialityName,
               isFavorite = isFavorite.isFavorite(name))
         }
      }
   }
}


