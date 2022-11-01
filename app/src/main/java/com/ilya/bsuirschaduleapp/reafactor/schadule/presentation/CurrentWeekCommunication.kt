package com.ilya.bsuirschaduleapp.reafactor.schadule.presentation

import com.ilya.bsuirschaduleapp.reafactor.core.Communication

/**
 * Created by HP on 01.11.2022.
 **/
interface CurrentWeekCommunication: Communication.Mutable<String> {

    class Base: CurrentWeekCommunication, Communication.UiUpdate<String>("1")
}