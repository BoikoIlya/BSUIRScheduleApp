package com.ilya.bsuirschaduleapp.reafactor.core

import androidx.lifecycle.viewModelScope
import com.ilya.bsuirschaduleapp.reafactor.schadule.presentation.ScheduleProgressCommunication
import kotlinx.coroutines.Job

/**
 * Created by HP on 25.10.2022.
 **/

interface FetchDataViewModel {
     fun fetchData(): Job
     fun refresh(): Job

    abstract class Abstract<S, T>(
        private val communication: Communication.Mutable<T>,
        private val dispatchers: Dispatchers,
        private val interactor: FetchDataInteractor<S, T>,
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
                interactor.fetchData(atFinish) {
                    communication.map(it)
                }
            }
        }

        final override fun refresh(): Job = dispatchers.launchBackground(viewModelScope){
            progressCommunication.map(true)
            handle {
                interactor.refresh(atFinish) {
                    communication.map(it)
                }
            }
        }
    }
}