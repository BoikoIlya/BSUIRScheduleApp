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
    Find<Job>{



abstract class Abstract<T,R>(
     progressCommunication:Communication.Mutable<Boolean>,
    private val communication: Communication.Mutable<List<R>>,
    private val listInteractor: ListInteractor<T, R>,
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

    override fun find(query: String): Job = dispatchers.launchBackground(viewModelScope){
        communication.map(listInteractor.find(query))
    }

}
    }



