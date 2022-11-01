package com.ilya.bsuirschaduleapp.reafactor.schadule.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.ilya.bsuirschaduleapp.reafactor.core.*
import com.ilya.bsuirschaduleapp.reafactor.currweek.data.CurrentWeekRepository
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.SubGroupRepository
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.SelectedScheduleIdRepository
import com.ilya.bsuirschaduleapp.reafactor.schadule.domain.ScheduleDomain
import com.ilya.bsuirschaduleapp.reafactor.schadule.domain.ScheduleInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

/**
 * Created by HP on 25.10.2022.
 **/

interface ScheduleViewModel {
    fun changeSubGroup(subGroup: Int): Job
    suspend fun collectSubGroup(lifecycleOwner: LifecycleOwner, flowCollector: FlowCollector<Int>)
    fun changeSelectedSchedule(id:String)
    fun currentWeek(): Job
    suspend fun collectCurrWeek( lifecycleOwner: LifecycleOwner,flowCollector: FlowCollector<String>)
    fun checkForUpdate(): Job

    @HiltViewModel
    class Base @Inject constructor(
        private val dispatchers: Dispatchers,
        communication: ScheduleCommunication,
        private val interactor: ScheduleInteractor,
        private val progressCommunication: ScheduleProgressCommunication,
        private val subGroupRepository: SubGroupRepository.Mutable,
        private val subGroupCommunication: SubGroupCommunication,
        val globalErrorCommunication: GlobalErrorCommunication.Mutable,
        private val selectedScheduleRepository: SelectedScheduleIdRepository,
        private val currWeekRepository: CurrentWeekRepository,
        private val currWeekCommunication: CurrentWeekCommunication
    ) : FetchDataViewModel.Abstract<ScheduleDomain, ScheduleUi>(communication,
        dispatchers,
        interactor,
        progressCommunication), ScheduleViewModel {

        init {
            subGroupCommunication.map(subGroupRepository.read())
            currentWeek()
            checkForUpdate()
        }

        override fun changeSelectedSchedule(id:String) {
            selectedScheduleRepository.save(id)
            refresh()
        }

        override fun currentWeek(): Job = dispatchers.launchBackground(viewModelScope){
            handle {
                currWeekCommunication.map(currWeekRepository.fetchData())
            }
        }

        override suspend fun collectCurrWeek(
            lifecycleOwner: LifecycleOwner,
            flowCollector: FlowCollector<String>
        ) = currWeekCommunication.collect(lifecycleOwner,flowCollector)

        override fun checkForUpdate(): Job = dispatchers.launchBackground(viewModelScope){
            handle {
                interactor.checkForUpdate()
            }
        }

        override fun changeSubGroup(subGroup: Int) = dispatchers.launchBackground(viewModelScope) {
            subGroupRepository.save(subGroup)
            fetchData()
            subGroupCommunication.map(subGroupRepository.read())
        }

        override suspend fun collectSubGroup(
            lifecycleOwner: LifecycleOwner,
            flowCollector: FlowCollector<Int>,
        ) = subGroupCommunication.collect(lifecycleOwner, flowCollector)




    }
}