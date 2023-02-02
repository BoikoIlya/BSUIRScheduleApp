package com.ilya.bsuirschaduleapp.reafactor.core

import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.FlowCollector

/**
 * Created by HP on 08.10.2022.
 **/
interface ProgressCollector {

    suspend fun collectProgress(lifecycleOwner: LifecycleOwner, flowCollector: FlowCollector<Boolean>)
}