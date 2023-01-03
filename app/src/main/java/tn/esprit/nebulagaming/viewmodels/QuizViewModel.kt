package tn.esprit.nebulagaming.viewmodels

import android.content.Context
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import tn.esprit.apimodule.NetworkClient
import tn.esprit.apimodule.models.GenericResp
import tn.esprit.apimodule.models.QuizAnswerBody
import tn.esprit.apimodule.utils.ResponseConverter
import tn.esprit.nebulagaming.utils.Resource
import tn.esprit.roommodule.dao.NotifDao
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val notifDao: NotifDao
) :
    DefaultViewModel() {

    fun answerQuiz(context: Context, quizId: String, failOrPass: Boolean) = liveData {

        emit(Resource.loading(data = null))

        try {

            val client = NetworkClient(context)

            val service = client.getQuizService()

            val actionResp = service.answerQuiz(
                quizId,
                QuizAnswerBody(getUserId(), failOrPass)
            )

            if (actionResp.isSuccessful)
                emit(Resource.success(data = actionResp.body()))
            else
                emit(
                    Resource.error(
                        data = null,
                        message = ResponseConverter.convert<GenericResp>(
                            actionResp.errorBody()!!.string()
                        ).data?.error!!
                    )
                )

        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error answering the quiz !"))
        }
    }

    fun deleteNotif(quizId: String) = runBlocking {
        notifDao.delete(notifDao.getByData(quizId))
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