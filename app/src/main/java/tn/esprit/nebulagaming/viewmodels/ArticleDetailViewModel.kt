package tn.esprit.nebulagaming.viewmodels

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import tn.esprit.apimodule.models.Article
import tn.esprit.authmodule.repos.UserAuthManager
import tn.esprit.nebulagaming.utils.HelperFunctions
import tn.esprit.nebulagaming.utils.HelperFunctions.toastMsg
import tn.esprit.roommodule.dao.BookmarksDao
import tn.esprit.roommodule.models.Bookmarks
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    private val authManager: UserAuthManager,
    private val bookmarksDao: BookmarksDao
) : ViewModel() {

    fun openArticlePage(context: Context, link: String) {
        HelperFunctions.launchURL(context, link)
    }

    fun addArticleToBookmarks(context: Context, article: Article) = runBlocking {
        try {

            bookmarksDao
                .create(
                    Bookmarks(
                        id = article.id!!,
                        title = article.title!!,
                        description = article.content!!,
                        image = article.image!!,
                        link = article.link!!,
                        userId = authManager.retrieveUserInfoFromStorage()!!.userId
                    )
                )
            toastMsg(context, "Added to bookmarks")
        } catch (e: Exception) {
            Log.e("ARTICLES", e.message!!)
            toastMsg(context, "Article already saved.")
        }
    }

    fun shareArticleWithPeople(context: Context, article: Article) {

        val chooserIntent = Intent.createChooser(Intent().apply {

            action = Intent.ACTION_SEND

            type = "text/plain"

            putExtra(Intent.EXTRA_TEXT, article.link!!)

            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION

        }, null)

        startActivity(context, chooserIntent, null)
    }
}