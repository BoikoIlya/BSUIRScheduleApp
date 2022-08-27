package com.ilya.bsuirschaduleapp.domain.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.ilya.bsuirschaduleapp.utils.Constance

@Entity(tableName = Constance.SCHEDULE_DB_SELECTED_GROUP_COLUMN)
data class SelectedGroup(
    val course: Int,
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val specialityName: String,
)