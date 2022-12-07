package tn.esprit.nebulagaming.viewmodels

import android.content.Context
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.liveData
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tn.esprit.apimodule.NetworkClient
import tn.esprit.apimodule.models.GenericResp
import tn.esprit.apimodule.models.OffreJob
import tn.esprit.apimodule.utils.ResponseConverter
import tn.esprit.authmodule.repos.UserAuthManagerImpl
import tn.esprit.nebulagaming.utils.Resource
import java.util.*
import javax.inject.Inject

@HiltViewModel
class OffreJobViewModel @Inject constructor(private val userManager: UserAuthManagerImpl) :
    DefaultViewModel() {

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

            processSaveOfferJob(
                jobTitle = inputs[0].text.toString(),
                jobType = jobType,
                jobDescription = inputs[1].text.toString(),
                jobStartDate = jobStartDate,
                jobEndDate = jobEndDate,
                jobPosition = inputs[3].text.toString(),
                jobAdress = inputs[2].text.toString(),
                jobWebsite = inputs[4].text.toString(),
                jobEmail = inputs[5].text.toString(),
                context = context
            )
    }

    private fun processSaveOfferJob(
        jobTitle: String,
        jobType: String,
        jobDescription: String,
        jobStartDate: String,
        jobEndDate: String,
        jobPosition: String,
        jobEmail: String,
        jobWebsite: String,
        jobAdress: String,
        context: Context
    ) {

        val authClient = NetworkClient(context)
        val iduser = userManager.retrieveUserInfoFromStorage()!!.userId
        val apiService = authClient.getOffreService()

        job = CoroutineScope(Dispatchers.IO).launch {

            val registerResp = apiService.addOfferJob(
                companyId = iduser,
                jobOfferBody = OffreJob(
                    jobTitle = jobTitle,
                    jobType = jobType,
                    jobDescription = jobDescription,
                    jobStartDate = jobStartDate,
                    jobAdress = jobAdress,
                    jobEmail = jobEmail,
                    jobWebsite = jobWebsite,
                    jobPosition = jobPosition,
                    jobEndDate = jobEndDate,
                    postedAt = Date()
                )
            )
            withContext(Dispatchers.Main) {
                try {
                    if (registerResp.isSuccessful)
                        onSuccess()
                    else {
                        Log.e("error", registerResp.errorBody().toString())
                        super.onError(registerResp)
                    }
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

    fun loadOffreJob(context: Context) =
        liveData(Dispatchers.IO) {

            val client = NetworkClient(context)

            val articlesServices = client.getOffreService()

            emit(Resource.loading(data = null))
            try {
                val response = articlesServices.getAllOffers()
                if (response.isSuccessful)
                    emit(
                        Resource.success(
                            data = response.body()
                        )
                    )
                else
                    emit(
                        Resource.error(
                            data = null,
                            message = ResponseConverter.convert<GenericResp>(
                                response.errorBody()!!.string()
                            ).data?.error!!
                        )
                    )
            } catch (ex: Exception) {
                emit(
                    Resource.error(
                        data = null,
                        message = ex.message
                            ?: "Unable to retrieve articles at the moment, please try again later."
                    )
                )
            }
        }

    fun loadOffreJobById(context: Context, id: String) =
        liveData(Dispatchers.IO) {

            val client = NetworkClient(context)

            val articlesServices = client.getOffreService()

            emit(Resource.loading(data = null))
            try {
                val response = articlesServices.getOffreJobbyId(id)
                if (response.isSuccessful)
                    emit(
                        Resource.success(
                            data = response.body(),

                            )
                    )
                else
                    emit(
                        Resource.error(
                            data = null,
                            message = ResponseConverter.convert<GenericResp>(
                                response.errorBody()!!.string()
                            ).data?.error!!
                        )
                    )

            } catch (ex: Exception) {
                emit(
                    Resource.error(
                        data = null,
                        message = ex.message
                            ?: "Unable to retrieve articles at the moment, please try again later."
                    )
                )
            }
        }
}