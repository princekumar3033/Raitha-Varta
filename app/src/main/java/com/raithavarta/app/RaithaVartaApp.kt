package com.raithavarta.app

import android.app.Application
import com.raithavarta.app.data.repository.TipRepository
import com.raithavarta.app.utils.PreferencesManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.raithavarta.app.worker.DailyTipWorker
import java.util.concurrent.TimeUnit

class RaithaVartaApp : Application() {

    lateinit var repository: TipRepository
        private set

    lateinit var preferencesManager: PreferencesManager
        private set

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        repository = TipRepository(this)
        preferencesManager = PreferencesManager(this)

        // Seed the database on first launch
        applicationScope.launch {
            repository.seedIfEmpty()
        }

        setupDailyNotification()
    }

    private fun setupDailyNotification() {
        val dailyWorkRequest = PeriodicWorkRequestBuilder<DailyTipWorker>(24, TimeUnit.HOURS)
            .build()
            
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "DailyTipWork",
            ExistingPeriodicWorkPolicy.KEEP,
            dailyWorkRequest
        )
    }
}
