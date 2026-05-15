package com.raithavarta.app.worker

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.raithavarta.app.MainActivity
import com.raithavarta.app.R
import com.raithavarta.app.utils.PreferencesManager
import kotlinx.coroutines.flow.first

class DailyTipWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val prefsManager = PreferencesManager(context)
        val notificationsEnabled = prefsManager.notificationsEnabled.first()

        if (!notificationsEnabled) {
            return Result.success()
        }

        showNotification()
        return Result.success()
    }

    private fun showNotification() {
        val channelId = "daily_tips_channel"
        val notificationId = 1001

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Daily Farming Tips"
            val descriptionText = "Get your daily agricultural tip updates"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_splash_logo) // Use splash logo
            .setContentTitle("Today's Daily Tips 🌱")
            .setContentText("Check out today's new farming tips!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    notify(notificationId, builder.build())
                }
            } else {
                notify(notificationId, builder.build())
            }
        }
    }
}
