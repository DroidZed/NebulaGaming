package tn.esprit.nebulagaming.viewmodels

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import tn.esprit.authmodule.repos.UserAuthManagerImpl
import javax.inject.Inject

@HiltViewModel
class ChatRoomViewModel @Inject constructor(private val userAuthManagerImpl: UserAuthManagerImpl) :
    ViewModel() {

    private val gson = Gson()

    fun retrieveConnectedUserId(): String? {
        return userAuthManagerImpl.retrieveUserInfoFromStorage()?.userId
    }

}