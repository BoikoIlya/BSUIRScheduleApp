package com.ilya.bsuirschaduleapp.reafactor.schadule.presentation

import com.ilya.bsuirschaduleapp.reafactor.core.Communication

/**
 * Created by HP on 31.10.2022.
 **/
interface SubGroupCommunication: Communication.Mutable<Int> {
    class Base: Communication.UiUpdate<Int>(0),SubGroupCommunication
}