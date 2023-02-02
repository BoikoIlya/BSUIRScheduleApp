package com.ilya.bsuirschaduleapp.reafactor.groupList.presentation

import com.ilya.bsuirschaduleapp.reafactor.core.Communication

/**
 * Created by HP on 09.10.2022.
 **/

interface GroupListProgressCommunication: Communication.Mutable<Boolean> {

    class Base: GroupListProgressCommunication, Communication.UiUpdate<Boolean>(false)
}