package com.ilya.bsuirschaduleapp.reafactor.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.FlowCollector

/**
 * Created by HP on 17.09.2022.
 **/
abstract class BaseViewModel<T>(
    private val communication: Communication.Mutable<T>,
    private val dispatchers: Dispatchers
    ): Communication.Collector<T>, ViewModel(){

    override suspend fun collect(lifecycleOwner: LifecycleOwner, collector: FlowCollector<T>) {
        communication.collect(lifecycleOwner, collector)
    }

    protected fun <T> handle(
        block: suspend () -> T
    ) = dispatchers.launchBackground(viewModelScope) {
        block.invoke()
        }


    }



