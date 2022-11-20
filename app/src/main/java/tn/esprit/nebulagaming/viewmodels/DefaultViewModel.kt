package tn.esprit.nebulagaming.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
open class DefaultViewModel @Inject constructor() : ViewModel() {

    protected open var job: Job? = null
    open var errorMessage = MutableLiveData<String?>()
    open var successMessage = MutableLiveData<String?>()

    open var loading = MutableLiveData<Boolean>()

    /**
     * No internet connection or server unavailable...
     */
    protected fun onError() {
        errorMessage.postValue("Error connecting to the server")
        loading.postValue(false)
    }

    protected open fun onSuccess() {
        successMessage.postValue("Success")
        loading.postValue(true)
    }

    override fun onCleared() {
        if (job != null) job?.cancel()

        super.onCleared()
    }
}