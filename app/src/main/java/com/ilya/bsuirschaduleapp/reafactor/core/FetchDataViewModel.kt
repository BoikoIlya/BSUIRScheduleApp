package com.ilya.bsuirschaduleapp.reafactor.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.ilya.bsuirschaduleapp.reafactor.schadule.presentation.ScheduleProgressCommunication
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.FlowCollector

/**
 * Created by HP on 25.10.2022.
 **/

interface FetchDataViewModel:ProgressCollector {
     fun fetchData(): Job
     fun refresh(): Job

    abstract class Abstract<S, T>(
        private val communication: Communication.Mutable<T>,
        private val dispatchers: Dispatchers,
        private val interactor: FetchDataInteractor<S,T>,
        private val progressCommunication: Communication.Mutable<Boolean>
    ) : BaseViewModel<T>(communication, dispatchers), FetchDataViewModel {

        init {
            fetchData()
        }

        protected val atFinish = {
            progressCommunication.map(false)
        }

        final override fun fetchData(): Job = dispatchers.launchBackground(viewModelScope) {
            progressCommunication.map(true)
            handle {
                interactor.fetchData({progressCommunication.map(false)}) {
                    communication.map(it)
                }
            }
        }

        final override fun refresh(): Job = dispatchers.launchBackground(viewModelScope){
            progressCommunication.map(true)
            handle {
                interactor.refresh({progressCommunication.map(false)}) {
                    communication.map(it)
                }
            }
        }

        override suspend fun collectProgress(
            lifecycleOwner: LifecycleOwner,
            flowCollector: FlowCollector<Boolean>
        ) = progressCommunication.collect(lifecycleOwner, flowCollector)
    }
}