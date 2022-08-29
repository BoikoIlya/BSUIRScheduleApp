package com.ilya.bsuirschaduleapp.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilya.bsuirschaduleapp.domain.models.SelectedGroup
import com.ilya.bsuirschaduleapp.domain.models.SelectedTeacher
import com.ilya.bsuirschaduleapp.domain.repositiries.DataBaseRepository
import com.ilya.bsuirschaduleapp.domain.repositiries.DataStoreRepository
import com.ilya.bsuirschaduleapp.domain.usecases.*
import com.ilya.bsuirschaduleapp.presentation.models.*
import com.ilya.bsuirschaduleapp.presentation.eventhandlers.EventHandler
import com.ilya.bsuirschaduleapp.utils.Constance
import com.ilya.bsuirschaduleapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val dataBaseRepository: DataBaseRepository,
    private val getScheduleByGroupNumberUseCase: GetScheduleByGroupNumberUseCase,
    private val getScheduleByTeacherUrlIdUseCase: GetScheduleByTeacherUrlIdUseCase,
    private val getListOfGroupsUseCase: GetListOfGroupsUseCase,
    private val getListOfTeachersUseCase: GetListOfTeachersUseCase,
    private val getCurrentWeekUseCase: GetCurrentWeekUseCase
): ViewModel(), EventHandler<SendDataEvent, ActionEvent> {

    private val _schedule = MutableStateFlow(ScheduleState())
    val schedule = _schedule.asStateFlow()

    private val _currWeek = MutableStateFlow(1)
    val currWeek = _currWeek.asStateFlow()

    private val _selectedSubGroup = MutableStateFlow(0)
    val selectedSubGroup = _selectedSubGroup.asStateFlow()

    private var _searchName = ""

    private val _groupList = MutableStateFlow(GroupListState(isLoading = true))
    val groupList = _groupList.asStateFlow()

    private val _selectedGroupList = MutableStateFlow(SelectedGroupListState())
    val selectedGroupList = _selectedGroupList.asStateFlow()

    private val _teacherList = MutableStateFlow(TeacherListState(isLoading = true))
    val teacherList = _teacherList.asStateFlow()

    private val _selectedTeacherList = MutableStateFlow(SelectedTeacherListState())
    val selectedTeacherList = _selectedTeacherList.asStateFlow()

    private val _teacherOrGroup = MutableStateFlow(0)
    val teacherOrGroup = _teacherOrGroup.asStateFlow()

    private var _selectedGroupOrTeacherPathForApi = ""

    private val _selectedGroupOrTeacherName = MutableStateFlow("")
    val selectedGroupOrTeacherName = _selectedGroupOrTeacherName.asStateFlow()

    private val _noConnection = MutableStateFlow(false)
    val noConnection = _noConnection.asStateFlow()


    init {
        getSelectedGroupOrTeacherApiPathFromDataStore()
        getSelectedGroupsFromDB()
        getSelectedTeachersFromDB()
        getSubGroupFromDataStore()
        getCurrWeekFromDataStore()
        getCurrWeekFromApi()
        getGroupListFromApi()
        getTeacherListFromApi()
        getSelectedGroupOrTeacherNameFromDataStore()
    }

    private suspend fun saveDataInDataStore(value: String, key: String){
        dataStoreRepository.putDataInDataStore(value,key)
    }

    private suspend fun getScheduleByGroupNumber() {
             getScheduleByGroupNumberUseCase(_selectedGroupOrTeacherPathForApi).collect {
                 when (it) {
                     is Resource.Success ->{
                         _schedule.value = ScheduleState(it.data!!, isLoading = false)
                     }
                     is Resource.Error -> {
                         _schedule.value = ScheduleState(error = it.message.toString(), isLoading = false)
                       _noConnection.value = true
                     }
                     is Resource.Loading -> _schedule.value = ScheduleState(isLoading = true)
                 }
         }
    }

    private suspend fun getScheduleByTeacherUrlId() {
            getScheduleByTeacherUrlIdUseCase(_selectedGroupOrTeacherPathForApi).collect {
                when (it) {
                    is Resource.Success -> {
                        _schedule.value = ScheduleState(it.data!!, isLoading = false)
                    }
                    is Resource.Error -> {
                        _schedule.value = ScheduleState(error = it.message.toString(), isLoading = false)
                        _noConnection.value = true
                    }
                    is Resource.Loading -> _schedule.value = ScheduleState(isLoading = true)
                }
            }
    }

    private fun getGroupListFromApi()  = viewModelScope.launch {
             getListOfGroupsUseCase().collect {
                  when (it) {
                     is Resource.Success ->{
                         _groupList.value =GroupListState(it.data!!)
                         insertGroupInDB() }
                     is Resource.Error -> {
                         _groupList.value =GroupListState(error = it.message.toString())
                         _noConnection.value = true
                     }
                     is Resource.Loading ->_groupList.value = GroupListState(isLoading = true)
                 }

             }
    }

    private suspend fun insertGroupInDB()
         {
            _groupList.value.data.forEach{
                dataBaseRepository.insertGroupInDB(it)
        }
    }

    private fun getGroupByNameFromDB() = viewModelScope.launch {
        dataBaseRepository.getGroupByNameFromDB(_searchName).collect{
            _groupList.value = GroupListState(it)
        }
    }

    private fun insertSelectedGroupInDB(selectedGroup: SelectedGroup) = viewModelScope.launch {
        dataBaseRepository.insertSelectedGroupInDB(selectedGroup)
    }

    private fun getSelectedGroupsFromDB() = viewModelScope.launch{
        dataBaseRepository.getSelectedGroupsFromDB().collect{
            _selectedGroupList.value = SelectedGroupListState(it)
        }
    }

    private fun deleteSelectedGroupFromDB(selectedGroup: SelectedGroup) = viewModelScope.launch {
        dataBaseRepository.deleteSelectedGroupFromDB(selectedGroup)
    }

    private fun getCurrWeekFromApi() = viewModelScope.launch {
        getCurrentWeekUseCase().collect{
            when(it){
                is Resource.Success ->{
                    _currWeek.value = it.data!!
                    saveDataInDataStore(_currWeek.value.toString(),Constance.CURRENT_WEEK_KEY)
                }
                is Resource.Error -> {
                    _groupList.value =GroupListState(error = it.message.toString())
                    _noConnection.value = true
                }
                is Resource.Loading ->_groupList.value = GroupListState(isLoading = true)
            }
            }
        }

    private fun getCurrWeekFromDataStore() =viewModelScope.launch  {
        dataStoreRepository.getDataFromDataStore(Constance.CURRENT_WEEK_KEY).collect{
            _currWeek.value =it.toInt()
        }
    }

   private fun getSubGroupFromDataStore()= viewModelScope.launch{
       dataStoreRepository.getDataFromDataStore( Constance.SELECTED_SUBGROUP_KEY).collect{
            _selectedSubGroup.value =it.toInt()
        }
    }

    private  fun getSelectedGroupOrTeacherApiPathFromDataStore()= viewModelScope.launch{
        dataStoreRepository.getDataFromDataStore(Constance.SELECTED_GROUP_OR_TEACHER_PATH_FOR_API_KEY).collect{
           _selectedGroupOrTeacherPathForApi = it
            if(it!=Constance.DATA_STORE_DEF_VALUE) {
                if (it.isDigitsOnly()) {
                    _teacherOrGroup.value=Constance.IS_GROUP
                    getScheduleByGroupNumber()
                } else
                {
                    _teacherOrGroup.value=Constance.IS_TEACHER
                    getScheduleByTeacherUrlId()
                }
            } else _schedule.value = ScheduleState(isLoading = false)
       }
    }

    private  fun getSelectedGroupOrTeacherNameFromDataStore()= viewModelScope.launch{
        dataStoreRepository.getDataFromDataStore(Constance.SELECTED_GROUP_OR_TEACHER_NAME_KEY).collect{
            _selectedGroupOrTeacherName.value = it
        }

    }

    private fun getTeacherListFromApi()  = viewModelScope.launch {
        getListOfTeachersUseCase().collect {
            when (it) {
                is Resource.Success ->{
                    _teacherList.value = TeacherListState(it.data!!)
                    insertTeacherInDB()
                }
                is Resource.Error -> {
                    _teacherList.value =TeacherListState(error = it.message.toString())
                    _noConnection.value = true
                }
                is Resource.Loading ->_teacherList.value = TeacherListState(isLoading = true)
            }
        }
    }

    private suspend fun insertTeacherInDB()
    {
        _teacherList.value.data.forEach{
            dataBaseRepository.insertTeacherInDB(it)
        }
    }

    private fun getTeacherByFioFromDB() = viewModelScope.launch {
        dataBaseRepository.getTeacherByFioFromDB(_searchName).collect{
            _teacherList.value = TeacherListState(it)
        }
    }

    private fun insertSelectedTeacherInDB(selectedTeacher: SelectedTeacher) = viewModelScope.launch {
        dataBaseRepository.insertSelectedTeacherInDB(selectedTeacher)
    }

    private fun getSelectedTeachersFromDB() = viewModelScope.launch{
        dataBaseRepository.getSelectedTeachersFromDB().collect{
            _selectedTeacherList.value = SelectedTeacherListState(it)
        }
    }

    private fun deleteSelectedTeacher(selectedTeacher: SelectedTeacher) = viewModelScope.launch {
        dataBaseRepository.deleteSelectedTeacherFromDB(selectedTeacher)
    }


    override fun obtainDataEvent(event: SendDataEvent) {
        when(event){
            is SendDataEvent.TeacherOrGroup -> _teacherOrGroup.value = event.TeacherOrGroup
            is SendDataEvent.ConnectionState->_noConnection.value = event.connectionState
            is SendDataEvent.SearchName->_searchName = event.searchName
        }
    }

    override fun obtainActionEvent(event: ActionEvent) {
        when(event){
            is ActionEvent.GetScheduleByGroupNumber->viewModelScope.launch{getScheduleByGroupNumber()}
            is ActionEvent.GetScheduleByTeacherUrlId->viewModelScope.launch{getScheduleByTeacherUrlId()}
            is ActionEvent.SaveDataInDataStore->viewModelScope.launch{saveDataInDataStore(event.value, event.key)}
            is ActionEvent.GetGroupByNameFromDB->getGroupByNameFromDB()
            is ActionEvent.GetTeacherByFioFromDB->getTeacherByFioFromDB()
            is ActionEvent.InsertSelectedGroupInDB->insertSelectedGroupInDB(event.selectedGroup)
            is ActionEvent.InsertSelectedTeacherInDB->insertSelectedTeacherInDB(event.selectedTeacher)
            is ActionEvent.DeleteSelectedGroupFromDB->deleteSelectedGroupFromDB(event.selectedGroup)
            is ActionEvent.DeleteSelectedTeacherFromDB->deleteSelectedTeacher(event.selectedTeacher)
        }
    }


}