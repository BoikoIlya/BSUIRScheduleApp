package com.ilya.bsuirschaduleapp.presentation.models

import com.ilya.bsuirschaduleapp.domain.models.SelectedGroup

data class SelectedGroupListState(
    val data: List<SelectedGroup> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)