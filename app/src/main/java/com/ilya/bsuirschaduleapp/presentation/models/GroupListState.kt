package com.ilya.bsuirschaduleapp.presentation.models

import com.ilya.bsuirschaduleapp.domain.models.Group

data class GroupListState(
    val data: List<Group> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)