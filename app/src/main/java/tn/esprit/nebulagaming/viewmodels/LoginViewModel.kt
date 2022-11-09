package tn.esprit.nebulagaming.viewmodels

import android.content.Context
import android.util.Patterns
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import tn.esprit.apimodule.NetworkClient
import tn.esprit.apimodule.models.AuthReqBody
import tn.esprit.apimodule.models.AuthResp
import tn.esprit.authmodule.repos.JWTManager
import tn.esprit.authmodule.repos.UserAuthManager
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val JwtManager: JWTManager,
    private val UserAuthManager: UserAuthManager
) : ViewModel() {

    var errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    private var job: Job? = null

    // onclick login button
    fun handleLogin(context: Context, inputs: List<EditText>, textLayouts: List<TextInputLayout>) {

        if (validateInputs(inputs, textLayouts).toList().all { it })

            processLogin(
                email = inputs[0].text.toString(),
                password = inputs[1].text.toString(),
                context
            )
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    private fun processLogin(email: String, password: String, context: Context) {

        val authClient = NetworkClient(context)

        val apiService = authClient.getAuthService()

        job = CoroutineScope(Dispatchers.IO).launch {

            val loginResp = apiService.login(AuthReqBody(email, password))
            withContext(Dispatchers.Main) {
                if (loginResp.isSuccessful)
                    onSuccess(loginResp.body()!!)
                else
                    onError(loginResp.body()!!)
            }
        }
    }

    private fun onSuccess(apiResponse: AuthResp) {
        val token = apiResponse.token!!
        val refresh = apiResponse.refresh!!

        val userId = JwtManager.extractUserIdFromJWT(token)

        UserAuthManager.saveUserInfoToStorage(userId, token, refresh)

        loading.value = false
    }

    private fun onError(apiResponse: AuthResp) {
        errorMessage.value = apiResponse.error!!
        loading.value = false
    }

    private fun validateInputs(
        inputs: List<EditText>,
        textLayouts: List<TextInputLayout>
    ): Pair<Boolean, Boolean> {

        when {

            inputs.all { it.text.isBlank() } -> {
                textLayouts.forEach {
                    it.apply {
                        error = "Field mustn't be blank!"
                        isErrorEnabled = true
                    }
                }
                return Pair(false, false)
            }

            !Patterns.EMAIL_ADDRESS.matcher(inputs[0].text.toString()).matches() -> {
                textLayouts[0].apply {
                    isErrorEnabled = true
                    error = "Invalid email !"
                }

                return Pair(false, true)
            }

            else -> {
                textLayouts.forEach {
                    it.apply {
                        error = null
                        isErrorEnabled = false
                    }
                }
                return Pair(true, true)
            }
        }
    }
}