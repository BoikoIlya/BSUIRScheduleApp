package com.ilya.bsuirschaduleapp.presentation.eventhandlers

interface ActionEventHandler<T> {

    fun obtainActionEvent(event: T)
}