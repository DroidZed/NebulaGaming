package tn.esprit.apimodule.repos

import retrofit2.Response
import retrofit2.http.*
import tn.esprit.apimodule.models.GenericResp
import tn.esprit.apimodule.models.QuizAnswerBody
import tn.esprit.apimodule.models.QuizModel

interface QuizApiService {

    companion object {

        private const val API_ID = "quiz/{id}"
    }

    @GET(API_ID)
    suspend fun getQuiz(@Path("id") quizId: String): Response<QuizModel>

    @PUT(API_ID)
    suspend fun answerQuiz(@Path("id") quizId: String, @Body quizAnswerBody: QuizAnswerBody): Response<GenericResp>
}