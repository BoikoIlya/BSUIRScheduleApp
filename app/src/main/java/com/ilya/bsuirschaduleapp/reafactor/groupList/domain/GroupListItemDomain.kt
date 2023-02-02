package com.ilya.bsuirschaduleapp.reafactor.groupList.domain

import com.ilya.bsuirschaduleapp.reafactor.groupList.presentation.GroupListItemUi
import com.ilya.bsuirschaduleapp.reafactor.core.IsFavorite

/**
 * Created by HP on 08.10.2022.
 **/
interface GroupListItemDomain {

   fun <T>map(mapper: Mapper<T>):T

   data class Base(
      val course: Int,
      val facultyName: String,
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


