package tn.esprit.nebulagaming.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.data.ChatBubble
import tn.esprit.nebulagaming.viewholders.ChatBubbleViewHolder

class ChatRoomAdapter(private val data: MutableList<ChatBubble>) : Adapter<ChatBubbleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatBubbleViewHolder =
        ChatBubbleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.chat_bubble, parent, false)
        )

    override fun onBindViewHolder(holder: ChatBubbleViewHolder, position: Int) =
        holder.bind(data[position])

    override fun getItemCount(): Int = data.size
}