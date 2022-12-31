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
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import tn.esprit.apimodule.NetworkClient
import tn.esprit.apimodule.models.GenericResp
import tn.esprit.apimodule.models.Product
import tn.esprit.apimodule.utils.ResponseConverter
import tn.esprit.nebulagaming.utils.Resource
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor() : DefaultViewModel() {

    //on click button new product
    fun handleSaveProduct(
        context: Context,
        inputs: List<TextInputEditText>,
        textLayouts: List<TextInputLayout>,
        category: String,
        image: File
    ) {
        if (validateInputs(inputs, textLayouts).toList().all { it })
            processSaveProduct(
                name = inputs[0].text.toString(),
                description = inputs[1].text.toString(),
                price = inputs[2].text.toString().toFloat(),
                quantity = inputs[3].text.toString().toInt(),
                status = inputs[4].text.toString(),
                category = category,
                image = image,
                context = context
            )
    }


    private fun processSaveProduct(
        name: String,
        description: String,
        price: Float,
        category: String,
        image: File,
        quantity: Int,
        status: String,
        context: Context
    ) {
        val authClient = NetworkClient(context)
        val apiService = authClient.getProductService()
        val requestBody: RequestBody =
            RequestBody.create("image/${image.extension}".toMediaTypeOrNull(), image)

        val fileToUpload: MultipartBody.Part =
            MultipartBody.Part.createFormData(
                "image",
                image.name,
                requestBody
            )
        job = CoroutineScope(Dispatchers.IO).launch {

            val ajouterproduct = apiService.uploadProduct(
                CategoryId = category,
                image = fileToUpload,
                product = Product(
                    name = name,
                    description = description,
                    price = price,
                    image = image.name,
                    quantity = quantity,
                    status = status
                )
            )
            withContext(Dispatchers.Main) {
                try {
                    if (ajouterproduct.isSuccessful)
                        onSuccess()
                    else {
                        Log.e("error", ajouterproduct.errorBody().toString())
                        super.onError(ajouterproduct)
                    }
                } catch (ex: Exception) {
                    super.onError()
                }
            }
        }

    }

    fun loadCategories(context: Context) = liveData(Dispatchers.IO) {
        val client = NetworkClient(context)
        val articlesServices = client.getCategoryService()
        emit(Resource.loading(data = null))
        try {
            val response = articlesServices.getAllCategories()
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