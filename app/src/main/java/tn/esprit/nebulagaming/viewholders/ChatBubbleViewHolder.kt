package tn.esprit.nebulagaming.viewholders

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.gson.Gson
import tn.esprit.authmodule.utils.UserInfo
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.data.ChatBubble
import tn.esprit.shared.Consts

class ChatBubbleViewHolder(itemView: View) : ViewHolder(itemView) {


    private var messageTv: TextView? = null
    private var bubbleHeader: TextView? = null
    private var chatBubbleContainer: ConstraintLayout? = null
    private var bubble: LinearLayout? = null
    private val gson = Gson()

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

        val connectedUserId = getLoggedInUserId(sharedPrefs)

        chatBubbleContainer?.setBackgroundResource(
            if (connectedUserId == chatBubble.senderId) R.drawable.sent_bubble else R.drawable.recieved_bubble
        )
    }

    private fun getLoggedInUserId(sharedPrefs: SharedPreferences) =
        gson.fromJson(sharedPrefs.getString(Consts.USER_KEY, ""), UserInfo::class.java).userId
}
