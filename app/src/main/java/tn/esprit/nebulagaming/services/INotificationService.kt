package tn.esprit.nebulagaming.services

import android.app.PendingIntent
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.coroutines.*
import tn.esprit.apimodule.NetworkClient
import tn.esprit.apimodule.models.TokenReqBody
import tn.esprit.roommodule.dao.NotifDao
import tn.esprit.roommodule.models.Notification
import tn.esprit.shared.Consts.APP_PREFS
import tn.esprit.shared.Consts.FCM_TOKEN


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

    fun onTokenReceived(context: Context, newToken: String) {

        val sharedPrefs = context.getSharedPreferences(APP_PREFS, MODE_PRIVATE)

        val currentToken = sharedPrefs.getString(FCM_TOKEN, "")

        val service = NetworkClient(context).getFcmService()

        try {
            if ((currentToken != "") and (currentToken != newToken)) CoroutineScope(Dispatchers.IO).launch {

                val resp = service.saveToken(TokenReqBody(currentToken!!, newToken))

                withContext(Dispatchers.Main) {

                    if (resp.isSuccessful)
                        sharedPrefs.edit().putString(FCM_TOKEN, newToken).apply()
                }
            }
            else CoroutineScope(Dispatchers.IO).launch {

                val resp = service.saveToken(TokenReqBody(newToken))

                withContext(Dispatchers.Main) {

                    if (resp.isSuccessful)
                        sharedPrefs.edit().putString(FCM_TOKEN, newToken).apply()
                }
            }
        } catch (e: Exception) {
            Log.e("FCM-TOKEN", e.message!!)
        }
    }
}