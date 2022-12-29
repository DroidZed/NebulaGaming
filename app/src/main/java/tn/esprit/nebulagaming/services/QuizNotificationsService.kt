package tn.esprit.nebulagaming.services

import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.authmodule.repos.UserAuthManager
import tn.esprit.nebulagaming.QuizActivity
import tn.esprit.roommodule.dao.NotifDao
import tn.esprit.shared.Consts.INTENT_QUIZ_ID
import tn.esprit.shared.Consts.QUIZ_NOTIF_CHANNEL_ID
import javax.inject.Inject

@AndroidEntryPoint
class QuizNotificationsService : FirebaseMessagingService(), INotificationService {

    @Inject
    lateinit var notifDao: NotifDao

    @Inject
    lateinit var userAuthManager: UserAuthManager

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val notifyIntent = Intent(this, QuizActivity::class.java)

        notifyIntent.apply {
            putExtra(INTENT_QUIZ_ID, remoteMessage.data["quizId"])
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }

        val notifyPendingIntent = PendingIntent.getActivity(
            this,
            0,
            notifyIntent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        cacheNotification(
            notifDao = notifDao,
            title = remoteMessage.notification!!.title!!,
            body = remoteMessage.notification!!.body!!,
            data = remoteMessage.data["quizId"]!!,
            userId = userAuthManager.retrieveUserInfoFromStorage()!!.userId,
            src = "QUIZ"
        )

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, QUIZ_NOTIF_CHANNEL_ID)
                .setSmallIcon(tn.esprit.nebulagaming.R.drawable.logonv)
                .setContentTitle(remoteMessage.notification!!.title)
                .setContentText(remoteMessage.notification!!.body)
                .setAutoCancel(true)
                .setContentIntent(notifyPendingIntent)
                .setSound(defaultSoundUri)

        val managerCompat = NotificationManagerCompat.from(this)
        managerCompat.notify(1, builder.build())
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}