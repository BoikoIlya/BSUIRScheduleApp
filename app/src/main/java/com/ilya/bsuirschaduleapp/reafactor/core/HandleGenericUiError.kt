package com.ilya.bsuirschaduleapp.reafactor.core

import com.ilya.bsuirschaduleapp.R
import javax.inject.Inject

/**
 * Created by HP on 19.09.2022.
 **/
class HandleGenericUiError(
    private val manageResource: ManageResource,
    private val globalErrorCommunication: GlobalErrorCommunication.Update
): HandleError {

    override suspend fun handle(error: Exception): Exception {
       globalErrorCommunication.map(manageResource.getString(R.string.unexpected_error_message))
        return error
    }
}