package tn.esprit.nebulagaming.viewmodels

import android.content.Context
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tn.esprit.apimodule.NetworkClient
import tn.esprit.apimodule.models.GenericResp
import tn.esprit.apimodule.utils.ResponseConverter
import tn.esprit.nebulagaming.utils.Resource
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor() : DefaultViewModel() {

    /**
    * @param body HashMap<String, Any> quizId, answererId, failOrPass
    * @param context Context
    */
    fun answerQuiz(context: Context, body: HashMap<String, Any>) {

        val client = NetworkClient(context)

        val service = client.getQuizService()

        job = CoroutineScope(Dispatchers.IO).launch {

            val actionResp = service.answerQuiz(body)

            withContext(Dispatchers.Main) {

                try {
                    if (actionResp.isSuccessful)
                        onSuccess(actionResp.message())
                    else
                        onError(actionResp)
                } catch (e: Exception) {
                    super.onError()
                }
            }
        }
    }

    fun fetchQuiz(context: Context, id: String) = liveData {

        val client = NetworkClient(context)

        val service = client.getQuizService()

        emit(Resource.loading(data = null))

        try {
            val resp = service.getQuiz(id)

            if (resp.isSuccessful) {

                println(resp.body())

                emit(Resource.success(data = resp.body()))

            } else emit(
                Resource.error(
                    data = null,
                    message = ResponseConverter.convert<GenericResp>(
                        resp.errorBody()!!.string()
                    ).data?.error!!
                )
            )

        } catch (e: Exception) {
            emit(Resource.error(message = e.message!!, data = null))
        }
    }
}