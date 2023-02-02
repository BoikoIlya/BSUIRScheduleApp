package com.ilya.bsuirschaduleapp.reafactor.dailyupdate

import android.app.AlarmManager
import android.content.Context
import androidx.work.*
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by HP on 22.01.2023.
 **/
interface WorkManagerWrapper {

   suspend fun start()

    class Base(
        private val context: Context
    ): WorkManagerWrapper{

        private val workManager = WorkManager.getInstance(context)

        override suspend fun start() {
            val request = PeriodicWorkRequestBuilder<DailyUpdate>(
                AlarmManager.INTERVAL_DAY, TimeUnit.MILLISECONDS
            ).setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .setRequiresBatteryNotLow(true)
                    .build()
            ).build()

            workManager.enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                request
            )
        }

        companion object{
            private const val WORK_NAME = "unique_work"
        }

    }
}