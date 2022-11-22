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
class VerifyCodeFGPViewModel  @Inject constructor() : DefaultViewModel() {

    // onclick send button
    fun handleVerifyCodeRequest(
        context: Context,
        email:String,
        codeInput: EditText,
        codeTLayout: TextInputLayout
    ) {
        if (validateInput(codeInput, codeTLayout))
            sendRequest(context, email, codeInput.text.toString())
    }

    private fun sendRequest(context: Context, email: String, code: String) {
        val authClient = NetworkClient(context)
        val apiService = authClient.getAuthService()
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = apiService.accountVerificationWithGencode(AuthReqBody(email, code))
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

    private fun validateInput(codeInput: EditText, codeTLayout: TextInputLayout): Boolean {
        when {
            codeInput.text.isBlank() -> {
                codeTLayout.apply {
                    error = "Field mustn't be blank!"
                    return false
                }
            }
            codeInput.text.length < 4 -> {
                codeTLayout.apply {
                    error = "Code must be 4 digits!"
                    return false
                }
            }
            else -> {
                codeTLayout.apply {
                    error = null
                    return true
                }
            }
        }
    }
}