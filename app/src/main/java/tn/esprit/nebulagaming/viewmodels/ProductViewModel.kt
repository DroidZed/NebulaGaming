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
import tn.esprit.apimodule.utils.ResponseConverter
import tn.esprit.authmodule.repos.UserAuthManagerImpl
import tn.esprit.nebulagaming.utils.Resource
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val userManager: UserAuthManagerImpl) :
    DefaultViewModel() {

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
                categoryId = category,
                image = image,
                context = context
            )
    }


    private fun processSaveProduct(
        name: String,
        description: String,
        price: Float,
        categoryId: String,
        image: File,
        quantity: Int,
        context: Context
    ) {
        val authClient = NetworkClient(context)
        val apiService = authClient.getProductService()
        val iduser = userManager.retrieveUserInfoFromStorage()!!.userId

        val requestBody: RequestBody =
            RequestBody.create("image/${image.extension}".toMediaTypeOrNull(), image)

        val fileToUpload: MultipartBody.Part =
            MultipartBody.Part.createFormData(
                "image",
                image.name,
                requestBody
            )
        job = CoroutineScope(Dispatchers.IO).launch {

            val map = HashMap<String, Any>()

            map["name"] = name
            map["description"] = description
            map["price"] = price
            map["image"] = image.name
            map["quantity"] = quantity
            map["publisher"] = iduser

            println("map : $map")

            val ajouterproduct = apiService.uploadProduct(
                categoryId = categoryId,
                image = fileToUpload,
                product = map
            )

            withContext(Dispatchers.Main) {
                try {
                    if (ajouterproduct.isSuccessful)
                        onSuccess(ajouterproduct.body()!!.message)
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

    fun loadCategories(context: Context) = liveData {
        emit(Resource.loading(data = null))
        try {
            val authClient = NetworkClient(context)
            val apiService = authClient.getCategoryService()
            val categories = apiService.getAllCategories()
            if (categories.isSuccessful) {
                emit(Resource.success(data = categories.body()))
            } else {
                emit(
                    Resource.error(
                        data = null,
                        message = ResponseConverter.convert<GenericResp>(
                            categories.errorBody()!!.string()
                        ).data?.error!!
                    )
                )
            }
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun loadMyProducts(context: Context) = liveData {
        emit(Resource.loading(data = null))
        try {
            val authClient = NetworkClient(context)
            val apiService = authClient.getProductService()
            val iduser = userManager.retrieveUserInfoFromStorage()!!.userId
            val products = apiService.getMyProducts(userId = iduser)
            if (products.isSuccessful) {
                emit(Resource.success(data = products.body()))
            } else {
                emit(
                    Resource.error(
                        data = null,
                        message = ResponseConverter.convert<GenericResp>(
                            products.errorBody()!!.string()
                        ).data?.error!!
                    )
                )
            }
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
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