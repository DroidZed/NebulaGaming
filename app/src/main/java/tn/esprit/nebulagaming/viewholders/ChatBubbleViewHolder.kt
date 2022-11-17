package tn.esprit.nebulagaming.viewholders

import android.content.Context
import android.view.View
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.data.ChatBubble
import tn.esprit.shared.Consts

class ChatBubbleViewHolder(itemView: View) : ViewHolder(itemView) {


    var messageTv: TextView? = null
    var timestampTv: TextView? = null

    init {
        messageTv = itemView.findViewById(R.id.message)
        timestampTv = itemView.findViewById(R.id.msgTime)
    }

    fun bind(chatBubble: ChatBubble) {
        messageTv!!.text = chatBubble.message
        timestampTv!!.text = chatBubble.time

        val context = itemView.context

        val sharedPrefs = context.getSharedPreferences(Consts.APP_PREFS, Context.MODE_PRIVATE)

        val connectedUserId = sharedPrefs.getString(Consts.U_ID_KEY, "")


        itemView.setBackgroundResource(
            if (connectedUserId == chatBubble.senderId) R.color.purple_200 else R.color.DarkBarColor
        )
    }
}
