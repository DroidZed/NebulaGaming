package tn.esprit.nebulagaming.viewmodels

import android.content.Context
import android.util.Patterns
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
class ForgetPasswordViewModel @Inject constructor() : DefaultViewModel() {

    // onclick send button
    fun handleForgetPasswordRequest(
        context: Context,
        emailInput: EditText,
        emailTLayout: TextInputLayout
    ) {

        if (validateInput(emailInput, emailTLayout))
            sendRequest(context, emailInput.text.toString())
    }

    private fun sendRequest(context: Context, email: String) {


        val authClient = NetworkClient(context)

        val apiService = authClient.getAuthService()

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = apiService.forgetPasswordRequest(AuthReqBody(email))
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
}