package com.ilya.bsuirschaduleapp.reafactor.core

import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListItemUi

/**
 * Created by HP on 17.09.2022.
 **/
interface CloudDataSource {

    suspend fun <T>handle(block: suspend ()->T):T


    abstract class Abstract(
        private val handleError: HandleError
    ):CloudDataSource{

        override suspend fun <T> handle(block: suspend () -> T): T =
            try
            {
                block.invoke()
            }catch (e: Exception){
                throw handleError.handle(e)
            }
    }
}