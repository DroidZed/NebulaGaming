package tn.esprit.nebulagaming.viewmodels

import android.content.Context
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tn.esprit.apimodule.NetworkClient
import tn.esprit.apimodule.models.AuthResp
import tn.esprit.apimodule.models.LoginReqBody
import tn.esprit.authmodule.repos.JWTManager
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val JwtManager: JWTManager
) : DefaultViewModel() {

    fun checkIfAdmin() =
        JwtManager.extractRoleFromJWT(authManager.retrieveUserInfoFromStorage()!!.token) == 0

    // onclick login button
    fun handleLogin(context: Context, inputs: List<EditText>, textLayouts: List<TextInputLayout>) {

        if (validateInputs(inputs, textLayouts).toList().all { it })

            processLogin(
                email = inputs[0].text.toString(),
                password = inputs[1].text.toString(),
                context
            )
    }

    private fun processLogin(email: String, password: String, context: Context) {

        val authClient = NetworkClient(context)

        val apiService = authClient.getAuthService()

        job = CoroutineScope(Dispatchers.IO).launch {

            try {
                val loginResp = apiService.login(LoginReqBody(email = email, password = password))
                withContext(Dispatchers.Main) {
                    if (loginResp.isSuccessful)
                        onLoginSuccess(loginResp.body()!!)
                    else
                        onError(loginResp)
                }

            } catch (e: Exception) {
                Log.e("ERROR LOGIN", e.message!!)
                super.onError()
            }
        }
    }

    private fun onLoginSuccess(apiResponse: AuthResp) {
        val token = apiResponse.token!!
        val refresh = apiResponse.refresh!!

        authManager.saveUserInfoToStorage(
            id = JwtManager.extractUserIdFromJWT(token),
            token = token,
            refresh = refresh,
            role = JwtManager.extractRoleFromJWT(token),
            status = JwtManager.extractStatusFromJWT(token)
        )
        errorMessage.postValue(null)
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
                    }
                }
                return Pair(false, false)
            }

            !Patterns.EMAIL_ADDRESS.matcher(inputs[0].text.toString()).matches() -> {
                textLayouts[0].apply {
                    isErrorEnabled = true
                    error = "Invalid email !"
                }
                textLayouts[1].apply {
                    error = null
                }

                return Pair(false, true)
            }

            else -> {
                textLayouts.forEach {
                    it.apply {
                        error = null
                    }
                }
                return Pair(true, true)
            }
        }
    }
}