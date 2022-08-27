package com.ilya.bsuirschaduleapp.presentation.eventhandlers

interface SendDataEventHandler<T> {

    fun obtainDataEvent(event: T)
}