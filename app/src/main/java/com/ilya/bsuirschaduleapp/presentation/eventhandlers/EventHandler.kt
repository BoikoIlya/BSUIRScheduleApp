package com.ilya.bsuirschaduleapp.presentation.eventhandlers

interface EventHandler<T, E> {

    fun obtainDataEvent(event: T)

    fun obtainActionEvent(event: E)
}