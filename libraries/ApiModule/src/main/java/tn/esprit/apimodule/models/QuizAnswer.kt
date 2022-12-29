package tn.esprit.apimodule.models

data class QuizAnswer(
    val choice: String,
    val isCorrect: Boolean? = false,
)
