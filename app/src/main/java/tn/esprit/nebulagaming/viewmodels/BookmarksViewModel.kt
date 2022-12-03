package tn.esprit.nebulagaming.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import tn.esprit.roommodule.dao.BookmarksDao
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val bookmarksDao: BookmarksDao
) : ViewModel() {

    fun loadBookmarkedArticles() = runBlocking { bookmarksDao.getAll() }
}