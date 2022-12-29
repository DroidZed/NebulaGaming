package tn.esprit.apimodule.models

data class QuizModel(
    val id: Int? = 0,
    val quizId: String,
    val question: String,
    val answers: List<QuizAnswer>,
    val type: String,
    val numberOfTries: Int
)
