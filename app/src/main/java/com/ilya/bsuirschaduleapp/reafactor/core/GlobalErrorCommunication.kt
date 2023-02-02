package com.ilya.bsuirschaduleapp.reafactor.core

/**
 * Created by HP on 18.09.2022.
 **/
interface GlobalErrorCommunication {

    interface Update: Communication.SuspendUpdate<String>
    interface Collect: Communication.Collector<String>
    interface Mutable: Update, Collect

    class Base: Communication.SingleUiUpdate<String>(), Mutable, Collect, Update
}