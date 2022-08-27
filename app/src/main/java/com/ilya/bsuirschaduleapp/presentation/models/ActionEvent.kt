package com.ilya.bsuirschaduleapp.presentation.models

import com.ilya.bsuirschaduleapp.domain.models.SelectedGroup
import com.ilya.bsuirschaduleapp.domain.models.SelectedTeacher

sealed class ActionEvent {
    object GetScheduleByGroupNumber: ActionEvent()
    object GetScheduleByTeacherUrlId: ActionEvent()
    data class SaveDataInDataStore(val value: String, val key: String): ActionEvent()
    object GetGroupByNameFromDB: ActionEvent()
    object GetTeacherByFioFromDB: ActionEvent()
    data class InsertSelectedGroupInDB(val selectedGroup: SelectedGroup): ActionEvent()
    data class InsertSelectedTeacherInDB(val selectedTeacher: SelectedTeacher): ActionEvent()
    data class DeleteSelectedGroupFromDB(val selectedGroup: SelectedGroup): ActionEvent()
    data class DeleteSelectedTeacherFromDB(val selectedTeacher: SelectedTeacher): ActionEvent()
}