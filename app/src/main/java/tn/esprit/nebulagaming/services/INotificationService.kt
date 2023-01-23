package tn.esprit.nebulagaming.services

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
}