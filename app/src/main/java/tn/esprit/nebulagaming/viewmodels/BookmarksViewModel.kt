package tn.esprit.nebulagaming.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import tn.esprit.authmodule.repos.UserAuthManager
import tn.esprit.roommodule.dao.UserDao
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(private val userDao: UserDao) : DefaultViewModel() {

    fun loadBookmarkedArticles() = runBlocking { userDao.getUserWithBookmarks(getUserId()) }
}