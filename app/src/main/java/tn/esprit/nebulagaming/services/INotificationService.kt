package tn.esprit.nebulagaming.services

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.coroutines.runBlocking
import tn.esprit.roommodule.dao.NotifDao
import tn.esprit.roommodule.models.Notification


interface INotificationService {

    fun cacheNotification(
        notifDao: NotifDao,
        userId: String,
        title: String,
        body: String,
        data: String,
        src: String
    ) = runBlocking {

        try {
            notifDao.create(
                Notification(
                    title = title,
                    body = body,
                    data = data,
                    userId = userId,
                    src = src
                )
            )
        } catch (e: Exception) {
            //
        }
    }

    fun <T> notify(
        context: Context,
        title: String?,
        message: String?,
        channelId: String,
        reqCode: Int,
        resultActivity: Class<T>
    ) {

        val notifyIntent = Intent(context, resultActivity)
        notifyIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val notifyPendingIntent = PendingIntent.getActivity(
            context,
            reqCode,
            notifyIntent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(context, channelId)
                .setSmallIcon(tn.esprit.nebulagaming.R.drawable.logonv)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(notifyPendingIntent)
                .setSound(defaultSoundUri)

        val managerCompat = NotificationManagerCompat.from(context)
        managerCompat.notify(1, builder.build())
    }

}