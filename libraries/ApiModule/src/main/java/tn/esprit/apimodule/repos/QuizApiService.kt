package tn.esprit.apimodule.repos

import retrofit2.Response
import retrofit2.http.*
import tn.esprit.apimodule.models.GenericResp
import tn.esprit.apimodule.models.QuizModel

interface QuizApiService {

    companion object {

        private const val API_ID = "quiz"
    }

    @GET(API_ID)
    suspend fun getQuiz(@Query("quizId") quizId: String): Response<QuizModel>

    @PUT(API_ID)
    @Multipart
    suspend fun answerQuiz(@PartMap quizAnswerBody: HashMap<String, Any>): Response<GenericResp>
}