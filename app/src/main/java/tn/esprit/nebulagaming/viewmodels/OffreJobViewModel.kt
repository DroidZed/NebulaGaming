package tn.esprit.nebulagaming.viewmodels

import android.content.Context
import android.util.Log
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tn.esprit.apimodule.NetworkClient
import tn.esprit.apimodule.models.OffreJob
import tn.esprit.authmodule.repos.UserAuthManager
import tn.esprit.authmodule.repos.UserAuthManagerImpl
import tn.esprit.nebulagaming.utils.Status
import javax.inject.Inject

@HiltViewModel
class OffreJobViewModel @Inject constructor(private val userManager: UserAuthManagerImpl) : DefaultViewModel() {

    //on click register button new offre job
    fun handlesaveOffreJob(
        context: Context,
        inputs: List<TextInputEditText>,
        textLayouts: List<TextInputLayout>,
        jobStartDate: String,
        jobEndDate: String,
        jobType: String,


        ) {

        if (validateInputs(inputs, textLayouts).toList().all { it })

            processSaveOffrejob(
                jobTitle = inputs[0].text.toString(),
                jobType = jobType,
                jobDescription = inputs[1].text.toString(),
                jobStartDate = jobStartDate,
                jobEndDate = jobEndDate,
                jobSalary = inputs[2].text.toString(),
                jobAdress = inputs[3].text.toString(),
                context = context
            )
    }

fun processSaveOffrejob(
    jobTitle: String,
    jobType: String,
    jobDescription: String,
    jobStartDate: String,
    jobEndDate: String,
    jobSalary: String,
    jobAdress: String,
    context: Context
    ) {

        val authClient = NetworkClient(context)
    val iduser = userManager.retrieveUserInfoFromStorage()!!.userId
        val apiService = authClient.getOffreService()

        job = CoroutineScope(Dispatchers.IO).launch {

            val registerResp = apiService.AddOffreJob(
                OffreJob(
                    jobTitle = jobTitle,
                    jobType = jobType,
                    jobDescription = jobDescription,
                    jobStartDate = jobStartDate,
                    jobEndDate = jobEndDate,
                    jobSalary = jobSalary,
                    jobAdress = jobAdress,
                    user_id = iduser

                )
            )
            withContext(Dispatchers.Main) {
                try {
                    if (registerResp.isSuccessful)
                        onSuccess()
                    else
                    {
                        Log.e("error", registerResp.errorBody().toString())
                        super.onError(registerResp)}
                } catch (ex: Exception) {
                    super.onError()
                }
            }
        }
    }

    private fun validateInputs(
        inputs: List<EditText>,
        textLayouts: List<TextInputLayout>
    ): List<Boolean> {
        var isValid = true
        val validationList = mutableListOf<Boolean>()
        for (i in inputs.indices) {
            if (inputs[i].text.toString().isEmpty()) {
                textLayouts[i].error = "This field is required"
                isValid = false
            } else {
                textLayouts[i].error = null
            }
            validationList.add(isValid)
        }
        return validationList
    }
}