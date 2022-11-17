package tn.esprit.nebulagaming.viewholders

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.data.ChatBubble
import tn.esprit.shared.Consts

class ChatBubbleViewHolder(itemView: View) : ViewHolder(itemView) {


    private var messageTv: TextView? = null
    private var bubbleHeader: TextView? = null
    private var chatBubbleContainer: ConstraintLayout? = null
    private var bubble: LinearLayout? = null

    init {
        messageTv = itemView.findViewById(R.id.message)
        bubbleHeader = itemView.findViewById(R.id.bubbleHeader)
        chatBubbleContainer = itemView.findViewById(R.id.chatBubbleContainer)
        bubble = itemView.findViewById(R.id.bubble)
    }

    fun bind(chatBubble: ChatBubble) {
        messageTv!!.text = chatBubble.message
        bubbleHeader!!.text = itemView.resources.getString(
            R.string.bubble_header, chatBubble.senderName, chatBubble.time
        )

        val context = itemView.context

        val sharedPrefs = context.getSharedPreferences(Consts.APP_PREFS, Context.MODE_PRIVATE)

        val connectedUserId = sharedPrefs.getString(Consts.U_ID_KEY, "")

        chatBubbleContainer?.setBackgroundResource(
            if (connectedUserId == chatBubble.senderId) R.drawable.sent_bubble else R.drawable.recieved_bubble
        )
    }
}
