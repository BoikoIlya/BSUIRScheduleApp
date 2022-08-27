package com.ilya.bsuirschaduleapp.presentation.models

import com.ilya.bsuirschaduleapp.domain.models.SelectedTeacher

data class SelectedTeacherListState(
    val data: List<SelectedTeacher> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)