package com.ilya.bsuirschaduleapp.reafactor.core

import androidx.annotation.StringRes
import com.ilya.bsuirschaduleapp.R

/**
 * Created by HP on 18.09.2022.
 **/
abstract class HandelUiErrorAbstract(
   private val manageResource: ManageResource,
   private val globalErrorCommunication: GlobalErrorCommunication.Update,
   private val handleError: HandleError  = HandleGenericUiError(manageResource, globalErrorCommunication)
):HandleError {

   @StringRes
   private val noConnectionExceptionMessage: Int = R.string.no_connection_message

   @StringRes
   private val serviceUnavailableExceptionMessage: Int = R.string.no_service_message

   override suspend fun handle(error: Exception): Exception {
     val messageId =  when(error){
         is NoInternetConnectionException->noConnectionExceptionMessage
         is ServiceUnavailableException->serviceUnavailableExceptionMessage
        else-> 0
      }
     return if(messageId == 0){
          handleError.handle(error)
      }
       else{
           globalErrorCommunication.map(manageResource.getString(messageId))
            error
     }
   }
}