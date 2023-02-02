package com.ilya.bsuirschaduleapp.reafactor.core

/**
 * Created by HP on 17.09.2022.
 **/
interface HandleError {

   suspend fun handle(error: Exception): Exception

}