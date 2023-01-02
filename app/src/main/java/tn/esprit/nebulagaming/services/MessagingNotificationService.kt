package tn.esprit.nebulagaming.services

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.authmodule.repos.UserAuthManager
import tn.esprit.nebulagaming.HomeActivity
import tn.esprit.roommodule.dao.NotifDao
import tn.esprit.shared.Consts.DMs_NOTIFICATIONS_ID
import javax.inject.Inject

@AndroidEntryPoint
class MessagingNotificationService : FirebaseMessagingService(), INotificationService {

    @Inject
    lateinit var notifDao: NotifDao

    @Inject
    lateinit var userAuthManager: UserAuthManager

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        cacheNotification(
            notifDao,
            remoteMessage.notification!!.title!!,
            remoteMessage.notification!!.body!!,
            remoteMessage.data["dmId"]!!,
            userAuthManager.retrieveUserInfoFromStorage()!!.userId,
            src = "DM"
        )

        notify(
            this,
            remoteMessage.notification!!.title,
            remoteMessage.notification!!.body,
            DMs_NOTIFICATIONS_ID,
            1,
            HomeActivity::class.java
        )
    }

    override fun `onNewToken`(token: String) {
        super.onNewToken(token)
        onTokenReceived(this, token)
    }
}