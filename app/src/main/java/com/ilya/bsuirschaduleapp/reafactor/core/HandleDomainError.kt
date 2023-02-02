package com.ilya.bsuirschaduleapp.reafactor.core

import java.net.UnknownHostException

/**
 * Created by HP on 18.09.2022.
 **/
class HandleDomainError: HandleError {
    override suspend fun handle(error: Exception): Exception =
       if(error is UnknownHostException)
             NoInternetConnectionException()
        else com.ilya.bsuirschaduleapp.reafactor.core.UnknownHostException()
}