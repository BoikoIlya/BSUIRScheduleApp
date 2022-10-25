package com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.ilya.bsuirschaduleapp.reafactor.core.*
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.presentation.ChangeFavoriteViewModel
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.ListInteractor
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.FlowCollector

/**
 * Created by HP on 17.09.2022.
 **/
interface ListViewModel<R>:
    ChangeFavorite,
    Communication.Collector<List<R>>,
    ProgressCollector,
    Find<Job>{



abstract class Abstract<T,R>(
    private val progressCommunication:Communication.Mutable<Boolean>,
    private val communication: Communication.Mutable<List<R>>,
    private val listInteractor: ListInteractor<List<T>, List<R>>,
    private val dispatchers: Dispatchers,
    changeFavorite: ChangeFavorite,
    updateFavorites: Communication.SuspendUpdate<Boolean>,
):ChangeFavoriteViewModel<T,R>(
    changeFavorite,
    updateFavorites,
    communication,
    dispatchers,
    listInteractor,
    progressCommunication
),
ListViewModel<R>{

   override suspend fun collectProgress(
        lifecycleOwner: LifecycleOwner,
        flowCollector: FlowCollector<Boolean>
    ) = progressCommunication.collect(lifecycleOwner, flowCollector)

    override fun find(query: String): Job = dispatchers.launchBackground(viewModelScope){
        communication.map(listInteractor.find(query))
    }

}
    }



