package com.ilya.bsuirschaduleapp.reafactor.schadule.presentation

import androidx.lifecycle.viewModelScope
import com.ilya.bsuirschaduleapp.reafactor.core.BaseViewModel
import com.ilya.bsuirschaduleapp.reafactor.core.Communication
import com.ilya.bsuirschaduleapp.reafactor.core.Dispatchers
import com.ilya.bsuirschaduleapp.reafactor.core.FetchDataViewModel
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.SubGroupRepository
import com.ilya.bsuirschaduleapp.reafactor.schadule.domain.ScheduleDomain
import com.ilya.bsuirschaduleapp.reafactor.schadule.domain.ScheduleInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

/**
 * Created by HP on 25.10.2022.
 **/

interface ScheduleViewModel {
    fun changeSubGroup(subGroup: Int): Job


    @HiltViewModel
    class Base @Inject constructor(
        private val dispatchers: Dispatchers,
        communication: ScheduleCommunication,
        interactor: ScheduleInteractor,
        progressCommunication: ScheduleProgressCommunication,
        private val subGroupRepository: SubGroupRepository.Save
    ) : FetchDataViewModel.Abstract<ScheduleDomain, ScheduleUi>(communication,
        dispatchers,
        interactor,
        progressCommunication), ScheduleViewModel {

       override fun changeSubGroup(subGroup: Int) = dispatchers.launchBackground(viewModelScope) {
            subGroupRepository.save(subGroup)
            fetchData()
        }

    }
}