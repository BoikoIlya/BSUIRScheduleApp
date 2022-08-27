package com.ilya.bsuirschaduleapp.presentation.models

import com.ilya.bsuirschaduleapp.domain.models.Teacher

data class TeacherListState(
    val data: List<Teacher> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)