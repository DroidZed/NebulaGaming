package tn.esprit.nebulagaming.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import retrofit2.Response
import tn.esprit.apimodule.models.GenericResp
import tn.esprit.apimodule.utils.ResponseConverter
import tn.esprit.authmodule.repos.UserAuthManager
import javax.inject.Inject

@HiltViewModel
open class DefaultViewModel @Inject constructor(
) : ViewModel() {

    @Inject
    lateinit var authManager: UserAuthManager

    protected open var job: Job? = null
    open var errorMessage = MutableLiveData<String?>()
    open var successMessage = MutableLiveData<String?>()

    /**
     * No internet connection or server unavailable...
     */
    protected fun onError() = errorMessage.postValue("Error connecting to the server")

    protected open fun <T> onError(response: Response<T>? = null) =
        errorMessage.postValue(
            ResponseConverter.convert<GenericResp>(response!!.errorBody()!!.string()).data!!.error
        )

    protected fun getUserId() = authManager.retrieveUserInfoFromStorage()!!.userId


    protected open fun onSuccess(msg: String? = "Success") {
        successMessage.postValue(msg)
        errorMessage.postValue(null)
    }

    override fun onCleared() {
        if (job != null) job?.cancel()
        super.onCleared()
    }
}