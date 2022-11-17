package tn.esprit.nebulagaming.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import tn.esprit.nebulagaming.R

class ChatViewHolder(itemView: View) : ViewHolder(itemView) {

    var name: TextView? = null
    var at: TextView? = null
    var senderImage: ImageView? = null
    var lastMsg: TextView? = null

    init {

        name = itemView.findViewById(R.id.name)
        at = itemView.findViewById(R.id.at)
        senderImage = itemView.findViewById(R.id.senderImage)
        lastMsg = itemView.findViewById(R.id.lastMsg)
    }

}