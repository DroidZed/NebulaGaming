package tn.esprit.nebulagaming.viewmodels

import android.content.Context
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tn.esprit.apimodule.NetworkClient
import tn.esprit.apimodule.models.AuthReqBody
import javax.inject.Inject

@HiltViewModel
class UnsecureChangePassViewModel @Inject constructor() : DefaultViewModel() {

    // onclick send button
    fun handleUnsecureChangePassRequest(
        context: Context,
        email: String,
        passwordInput: EditText,
        passwordTLayout: TextInputLayout,
    ) {
        if (validateInput(passwordInput, passwordTLayout))
            sendRequest(context, email, passwordInput.text.toString())
    }

    private fun sendRequest(context: Context, email: String, password: String) {
        val authClient = NetworkClient(context)
        val apiService = authClient.getAuthService()
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = apiService.unsecureChangePassword(AuthReqBody(email, null, password))
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful)
                        onSuccess(response.body()!!.message!!)
                    else
                        onError(response)
                } catch (ex: NullPointerException) {
                    super.onError()
                }
            }
        }
    }

    private fun validateInput(passwordInput: EditText, passwordTLayout: TextInputLayout): Boolean {
        when {
            passwordInput.text.isBlank() -> {
                passwordTLayout.apply {
                    error = "Field mustn't be blank!"
                    return false
                }
            }
            passwordInput.text.length < 8 -> {
                passwordTLayout.apply {
                    error = "Password must be at least 8 characters!"
                    return false
                }
            }
            else -> {
                passwordTLayout.apply {
                    error = null
                    return true
                }
            }
        }
    }
}