package tn.esprit.nebulagaming.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import tn.esprit.authmodule.repos.UserAuthManager
import tn.esprit.roommodule.dao.NotifDao
import tn.esprit.roommodule.dao.UserDao
import tn.esprit.roommodule.models.Notification
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val notifDao: NotifDao,
    private val userDao: UserDao,
    private val userAuthManager: UserAuthManager
) : ViewModel() {

    var notifBadgeNumber = notifDao.countNotifications()

    fun getUserNotifications() = runBlocking {
        userDao.getUserWithNotifications(userAuthManager.retrieveUserInfoFromStorage()!!.userId)!!.notifications
    }

    fun clearNotification(n: Notification) = runBlocking { notifDao.delete(n) }
}