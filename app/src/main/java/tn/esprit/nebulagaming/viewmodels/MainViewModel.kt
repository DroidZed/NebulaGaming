package tn.esprit.nebulagaming.viewmodels

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : DefaultViewModel() {


    fun isUserLoggedIn(): Boolean {
        return authManager.checkIfUserLoggedIn()
    }
}