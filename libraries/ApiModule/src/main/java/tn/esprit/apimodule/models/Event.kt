package tn.esprit.apimodule.models

data class Event(
    val _id: String,
    val title: String,
    val description: String,
    val startDateTime: String,
    val endDateTime: String,
    val topic: String,
    val bonus: Int
)
