package com.ilya.bsuirschaduleapp.presentation.models

sealed class SendDataEvent{
    data class TeacherOrGroup(val TeacherOrGroup: Int): SendDataEvent()
    data class ConnectionState(val connectionState: Boolean): SendDataEvent()
    data class SearchName(val searchName: String): SendDataEvent()
}
