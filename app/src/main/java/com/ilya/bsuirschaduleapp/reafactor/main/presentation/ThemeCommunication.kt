package com.ilya.bsuirschaduleapp.reafactor.main.presentation

import com.ilya.bsuirschaduleapp.reafactor.core.Communication

/**
 * Created by HP on 02.11.2022.
 **/
interface ThemeCommunication: Communication.Mutable<Boolean> {

    class Base: ThemeCommunication, Communication.UiUpdate<Boolean>(false)
}