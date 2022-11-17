package tn.esprit.nebulagaming.data

data class ChatBubble(
    val senderName: String,
    val message: String,
    val time: String,
    val senderId: String,
    val receiverId: String,
)
