package tn.esprit.nebulagaming.viewmodels

import android.util.Patterns
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import tn.esprit.apimodule.models.AuthReqBody
import tn.esprit.apimodule.models.GenericResp
import tn.esprit.apimodule.repos.AuthApiService
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(
    private val apiService: AuthApiService,
) :
    ViewModel() {

    private var job: Job? = null
    var errorMessage = MutableLiveData<String>()
    var apiMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    // onclick send button
    fun handleForgetPasswordRequest(emailInput: EditText, emailTLayout: TextInputLayout) {

        if (validateInput(emailInput, emailTLayout))
            sendRequest(emailInput.text.toString())
    }

    private fun sendRequest(email: String) {

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = apiService.forgetPasswordRequest(AuthReqBody(email))
            withContext(Dispatchers.Main) {
                if (response.isSuccessful)
                    onSuccess(response.body()!!)
                else
                    onError(response.body()!!)
            }
        }

    }

    private fun validateInput(emailInput: EditText, emailTLayout: TextInputLayout): Boolean {
        when {

            emailInput.text.isBlank() -> {
                emailTLayout.apply {
                    error = "Field mustn't be blank!"
                }
                return false
            }

            !Patterns.EMAIL_ADDRESS.matcher(emailInput.text.toString()).matches() -> {
                emailTLayout.apply {
                    error = "Invalid email !"
                }
                return false
            }

            else -> {
                emailTLayout.apply {
                    error = null
                }
                return true
            }
        }
    }

    override fun onCleared() {
        job?.cancel()
        super.onCleared()
    }

    private fun onSuccess(apiResponse: GenericResp) {
        apiMessage.value = apiResponse.message!!
        loading.value = false
    }

    private fun onError(apiResponse: GenericResp) {
        errorMessage.value = apiResponse.error!!
        loading.value = false
    }

}