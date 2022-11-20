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
import retrofit2.Response
import tn.esprit.apimodule.NetworkClient
import tn.esprit.apimodule.models.AuthResp
import tn.esprit.apimodule.models.LoginReqBody
import tn.esprit.apimodule.utils.ResponseConverter
import tn.esprit.authmodule.repos.JWTManager
import tn.esprit.authmodule.repos.UserAuthManager
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val JwtManager: JWTManager,
    private val UserAuthManager: UserAuthManager
) : DefaultViewModel() {

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

            val loginResp = apiService.login(LoginReqBody(email = email, password = password))
            withContext(Dispatchers.Main) {
                try {
                    if (loginResp.isSuccessful)
                        onSuccess(loginResp.body()!!)
                    else
                        onError(loginResp)
                } catch (e: Exception) {
                    super.onError()
                }
            }
        }
    }

    /*
        private fun runLogin(email: String, password: String, context: Context) =
            liveData(Dispatchers.IO) {

                val authClient = NetworkClient(context)

                val apiService = authClient.getAuthService()

                emit(Resource.loading(data = null))
                try {
                    emit(
                        Resource.success(
                            data = apiService.login(
                                LoginReqBody(
                                    email = email,
                                    password = password
                                )
                            )
                        )
                    )
                } catch (ex: Exception) {
                    emit(
                        Resource.error(
                            data = null,
                            message = ex.message ?: "Error connecting to the server"
                        )
                    )
                }
            }
    */
    private fun onSuccess(apiResponse: AuthResp) {
        val token = apiResponse.token!!
        val refresh = apiResponse.refresh!!

        val userId = JwtManager.extractUserIdFromJWT(token)
        val role = JwtManager.extractRoleFromJWT(token)

        UserAuthManager.saveUserInfoToStorage(
            id = userId,
            token = token,
            refresh = refresh,
            role = role
        )
        errorMessage.postValue(null)
        loading.postValue(false)
    }

    /**
     * when an error response returns from the server [400 - 500]
     */
    private fun onError(response: Response<AuthResp>) {
        errorMessage.postValue(
            ResponseConverter.convert<AuthResp>(response.errorBody()!!.string()).data!!.error
        )
        loading.postValue(false)
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