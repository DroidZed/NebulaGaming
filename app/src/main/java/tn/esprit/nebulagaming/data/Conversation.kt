package tn.esprit.nebulagaming.data

data class Conversation(
    val otherId: String,
    val otherName: String,
    val otherImage: String,
    val at: String,
    var isRead: Boolean,
    val lastMessage: String
)
