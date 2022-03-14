package com.example.assignment2

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class MyNotificationManager(var context: Context) {

    private val ID_NOTIFICATION = 200
    private val ID_NOTIFICATION_ = 200
    private val CHANNEL_ID = "CHANNEL_ID"

    fun showSmallNotification(
        id: Int,
        title: String,
        message: String,
        intent: Intent?
    ) {

        val pendingIntent = PendingIntent.getActivity(
            context,
            ID_NOTIFICATION,
            intent,
            PendingIntent.FLAG_CANCEL_CURRENT
        )

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)

        val notification: Notification
        notification = builder.setSmallIcon(R.drawable.ic_launcher_background).setContentIntent(pendingIntent)
            .setContentTitle(title).setContentText(message).setAutoCancel(false)
            .setPriority(NotificationManager.IMPORTANCE_HIGH).build()

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Channel Notif",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.setShowBadge(false)
            channel.description = message
            channel.enableLights(true)
            channel.enableVibration(true)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(id, notification)
    }


    fun getNotificationID(): Int {
        return ID_NOTIFICATION_
    }


    fun getNotification(context: Context) : Notification {
        val channelID = "channel_id2"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val name: CharSequence = "Foreground Service"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelID, name, importance)
            channel.setShowBadge(false)
            channel.description = "sdfsdfsdfsdfsdff"
            channel.enableLights(false)
            channel.enableVibration(false)
            channel.setSound(null, null)

            assert(manager != null)
            manager.createNotificationChannel(channel)

            val pendingIntent = PendingIntent.getActivity(
                context, 0, Intent(
                    context, MainActivity::class.java
                ), 0
            )

            val builder = Notification.Builder(context, channelID)
            builder.setContentTitle("Foreground Service").setCategory(Notification.CATEGORY_SERVICE)
                .setSmallIcon(R.drawable.ic_notifications).setContentIntent(pendingIntent).setAutoCancel(false)

            val notification = builder.build()
            return  notification

        }else {

            val pendingIntent = PendingIntent.getActivity(
                context, 0, Intent(
                    context, MainActivity::class.java
                ), 0
            )

            val builder = NotificationCompat.Builder(context)
                .setCategory(Notification.CATEGORY_SERVICE)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
            return builder.build()

        }
    }
}