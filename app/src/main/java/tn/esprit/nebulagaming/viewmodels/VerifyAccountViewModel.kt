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
class VerifyAccountViewModel @Inject constructor() : DefaultViewModel() {

    // onclick send button
    fun handleVerifyAccountRequest(
        context: Context,
        email: String,
        codeInput: EditText,
        codeTLayout: TextInputLayout
    ) {
        if (validateInput(codeInput, codeTLayout))
            sendRequest(context, email, codeInput.text.toString())
    }

    //validateInput
    private fun validateInput(codeInput: EditText, codeTLayout: TextInputLayout): Boolean {
        var isValid = true
        if (codeInput.text.toString().isEmpty()) {
            codeTLayout.error = "Code is required"
            isValid = false
        } else {
            codeTLayout.error = null
        }
        return isValid
    }

    private fun sendRequest(context: Context, email: String, code: String) {
        val authClient = NetworkClient(context)
        val apiService = authClient.getAuthService()
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = apiService.accountVerificationWithGencode(AuthReqBody(email, code))
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful)
                        onSuccess()
                    else
                        super.onError(response)
                } catch (ex: NullPointerException) {
                    super.onError()
                }
            }
        }
    }
}