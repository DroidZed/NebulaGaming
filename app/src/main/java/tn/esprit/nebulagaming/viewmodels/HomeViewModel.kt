package tn.esprit.nebulagaming.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prof.rssparser.Channel
import com.prof.rssparser.Parser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import tn.esprit.authmodule.repos.UserAuthManager
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authManager: UserAuthManager
) : ViewModel() {

    private val url = "https://kotaku.com/rss"

    private val okHttpClient by lazy {
        OkHttpClient()
    }

    private val _snackBar = MutableLiveData<String?>()
    val snackBar: MutableLiveData<String?>
        get() = _snackBar

    private val _rssChannel = MutableLiveData<Channel>()
    val rssChannel: LiveData<Channel>
        get() = _rssChannel

    fun onSnackBarShowed() {
        _snackBar.value = null
    }

    fun handleLogOut() {
        authManager.logOutUser()
    }

    fun fetchFeed(parser: Parser) {

        viewModelScope.launch {
            try {
                val channel = parser.getChannel(url)
                _rssChannel.postValue(channel)
            } catch (e: Exception) {
                e.printStackTrace()
                _snackBar.value = "An error has occurred. Please retry"
                _rssChannel.postValue(
                    Channel(
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        mutableListOf(),
                        null
                    )
                )
            }
        }
    }

    fun fetchForUrlAndParseRawData(parser: Parser) {

        viewModelScope.launch(Dispatchers.IO) {
            val request = Request.Builder()
                .url(url)
                .build()
            val result = okHttpClient.newCall(request).execute()
            val raw = runCatching { result.body?.string() }.getOrNull()
            if (raw == null) {
                _snackBar.postValue("Something went wrong!")
            } else {
                val channel = parser.parse(raw)
                _rssChannel.postValue(channel)
            }
        }
    }


}