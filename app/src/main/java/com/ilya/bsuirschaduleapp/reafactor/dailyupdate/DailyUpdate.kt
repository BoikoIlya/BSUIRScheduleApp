package com.ilya.bsuirschaduleapp.reafactor.dailyupdate

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.ScheduleRepository
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cache.ScheduleCache
import com.ilya.bsuirschaduleapp.reafactor.schadule.domain.ScheduleDomain
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by HP on 22.01.2023.
 **/
@HiltWorker
class DailyUpdate @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val repository: ScheduleRepository<ScheduleCache, ScheduleDomain>
): CoroutineWorker(context, params) {
    
    override suspend fun doWork(): Result {
        return try {
            repository.refresh()
            Result.success()
        }catch (e: Exception){
            Result.failure()
        }
    }
}