package com.recodigo.kotlinfirebasedemo.utils

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.recodigo.kotlinfirebasedemo.MainActivity
import com.recodigo.kotlinfirebasedemo.R

/**
 * Created by SAUL on 09/11/2020.
 */
class MyFirebaseMessagingService : FirebaseMessagingService() {
    // IT IS CALLED WHEN THE TOKEN IS CREATED
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        val notification = remoteMessage.notification
        val title: String = notification!!.title!!
        val msg: String = notification.body!!

        sendNotification(title, msg)
    }

    private fun sendNotification(title: String, msg: String) {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            MyNotification.NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_ONE_SHOT
        )
        val notification = MyNotification(this, MyNotification.CHANNEL_ID_NOTIFICATIONS)
        notification.build(R.drawable.ic_launcher_foreground, title, msg, pendingIntent)
        notification.addChannel("Notificaciones")
        notification.createChannelGroup(
            MyNotification.CHANNEL_GROUP_GENERAL,
            R.string.notification_channel_group_general
        )
        notification.show(MyNotification.NOTIFICATION_ID)
    }
}