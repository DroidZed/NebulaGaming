package tn.esprit.nebulagaming.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import tn.esprit.apimodule.NetworkClient
import tn.esprit.nebulagaming.utils.Resource
import tn.esprit.roommodule.dao.BookmarksDao
import tn.esprit.roommodule.models.Bookmarks
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val bookmarksDao: BookmarksDao
) : ViewModel() {

    fun loadRssArticles(pageNumber: Int, context: Context) =
        liveData(Dispatchers.IO) {

            val client = NetworkClient(context)

            val articlesServices = client.getArticleService()

            emit(Resource.loading(data = null))
            try {
                emit(
                    Resource.success(
                        data = articlesServices.downloadArticles(pageNumber).body()
                    )
                )
            } catch (ex: Exception) {
                emit(
                    Resource.error(
                        data = null,
                        message = ex.message
                            ?: "Unable to retrieve articles at the moment, please try again later."
                    )
                )
            }
        }

}