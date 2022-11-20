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
import tn.esprit.apimodule.models.RegisterReqBody
import tn.esprit.apimodule.models.UserProfileResponse
import tn.esprit.apimodule.models.UserRegister
import tn.esprit.apimodule.utils.ResponseConverter
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : DefaultViewModel() {

    //on click register button
    fun handleRegister(
        Context: Context,
        inputs: List<EditText>,
        textLayouts: List<TextInputLayout>
    ) {

   if (validateInputs(inputs, textLayouts).toList().all { it })

  processRegister(
        email = inputs[1].text.toString(),
        password = inputs[2].text.toString(),
        name = inputs[0].text.toString(),
        phone = inputs[3].text.toString().toInt(),
        role = 1,
        Context
    )
}

private fun processRegister(
    email: String,
    password: String,
    name: String,
    phone:Int,
    role:Int,
    Context: Context
) {

    val authClient = NetworkClient(Context)

    val apiService = authClient.getAuthService()

    job = CoroutineScope(Dispatchers.IO).launch {

        val registerResp = apiService.register(
            UserRegister(
                email = email,
                password = password,
                name = name,
                phone = phone,
                role = role

            )
        )
        withContext(Dispatchers.Main) {
            try {
                if (registerResp.isSuccessful)
                    onSuccess()
                else
                    onError(registerResp)
            } catch (e: Exception) {
                super.onError()
            }
        }
    }
}

    //success  response with RegisterReqBody
    override fun onSuccess() {
        super.onSuccess()
        errorMessage.postValue(null)
        loading.postValue(false)

    }

    private fun onError(response: Response<RegisterReqBody>) {
        errorMessage.postValue(
            ResponseConverter.convert<RegisterReqBody>(response.errorBody()!!.string()).data!!.errormsj
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

   !Patterns.EMAIL_ADDRESS.matcher(inputs[1].text.toString()).matches() -> {
       textLayouts[1].apply {
           isErrorEnabled = true
           error = "Invalid email !"
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
