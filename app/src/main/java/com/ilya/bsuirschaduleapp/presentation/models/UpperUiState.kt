package com.ilya.bsuirschaduleapp.presentation.models

data class UpperUiState(
    val index: Int,
    var dayOfWeek: Int,
    val weekNumber: Int,
    val error: String = "",
)
