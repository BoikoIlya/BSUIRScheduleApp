package com.ilya.bsuirschaduleapp.reafactor.main.presentation

import androidx.lifecycle.viewModelScope
import com.ilya.bsuirschaduleapp.reafactor.core.BaseViewModel
import com.ilya.bsuirschaduleapp.reafactor.core.Dispatchers
import com.ilya.bsuirschaduleapp.reafactor.dailyupdate.WorkManagerWrapper
import com.ilya.bsuirschaduleapp.reafactor.main.data.ThemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by HP on 02.11.2022.
 **/
interface MainViewModel {
    fun saveTheme(value: Boolean)
    fun readTheme(): Boolean

    @HiltViewModel
    class Base @Inject constructor(
        private val communication: ThemeCommunication,
        private val dispatchers: Dispatchers,
        private val repository: ThemeRepository,
        private val workManagerWrapper: WorkManagerWrapper
    ) : BaseViewModel<Boolean>(communication, dispatchers),MainViewModel {

        init {
         readTheme()

            dispatchers.launchBackground(viewModelScope){
                workManagerWrapper.start()
            }
        }

        override fun saveTheme(value: Boolean) = repository.saveTheme(value)


        override fun readTheme() = repository.readTheme()



    }
}