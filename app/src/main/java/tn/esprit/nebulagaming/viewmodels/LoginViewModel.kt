package tn.esprit.nebulagaming.viewmodels

import android.util.Patterns
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import tn.esprit.apimodule.NetworkModule
import tn.esprit.apimodule.models.AuthReqBody
import tn.esprit.apimodule.models.AuthResp
import tn.esprit.authmodule.repos.JWTManagerImpl
import tn.esprit.authmodule.repos.UserAuthManagerImpl
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val UserAuthManager: UserAuthManagerImpl,
    private val JwtManager: JWTManagerImpl,
    private val networkModule: NetworkModule
) : ViewModel() {

    private val retrofitClient = networkModule.providesRetrofit()

    private var job: Job? = null
    var errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()


    // onclick login button
    fun handleLogin(inputs: List<EditText>, textLayouts: List<TextInputLayout>) {

        if (validateInputs(inputs, textLayouts).toList().all { it })

            processLogin(inputs[0].text.toString(), inputs[1].text.toString())
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    // TODO: login with retrofit
    private fun processLogin(email: String, password: String) {

        val authApiService = networkModule.providesAuthAPI(retrofitClient)

        job = CoroutineScope(Dispatchers.IO).launch {

            val loginResp = authApiService.login(AuthReqBody(email, password))
            withContext(Dispatchers.Main) {
                if (loginResp.isSuccessful)
                    onSuccess(loginResp.body()!!)
                else
                    onError(loginResp.body()!!)
            }
        }
    }

    private fun onSuccess(loginResp: AuthResp) {
        val token = loginResp.token!!

        val userId = JwtManager.extractUserIdFromJWT(token)

        UserAuthManager.saveUserInfoToStorage(userId, token)

        loading.value = false

    }

    private fun onError(loginResp: AuthResp) {
        errorMessage.value = loginResp.error!!
        loading.value = false
    }

    private fun validateInputs(
        inputs: List<EditText>,
        textLayouts: List<TextInputLayout>
    ): Pair<Boolean, Boolean> {

        val emailET = inputs[0]

        when {

            inputs.all { it.text.isBlank() } -> {
                textLayouts.forEach {
                    it.error = "Field mustn't be blank!"
                    it.isErrorEnabled = true
                }
                return Pair(false, false)
            }

            !Patterns.EMAIL_ADDRESS.matcher(emailET.text.toString()).matches() -> return Pair(
                false,
                true
            )

            else -> return Pair(true, true)
        }
    }
}