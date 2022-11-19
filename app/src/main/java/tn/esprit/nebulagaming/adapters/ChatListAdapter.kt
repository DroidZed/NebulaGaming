package tn.esprit.nebulagaming.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView.Adapter
import tn.esprit.nebulagaming.ChatRoomActivity
import tn.esprit.nebulagaming.HomeActivity
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.data.Conversation
import tn.esprit.nebulagaming.utils.HelperFunctions.usePicasso
import tn.esprit.nebulagaming.viewholders.ChatViewHolder

class ChatListAdapter(private val data: MutableList<Conversation>) : Adapter<ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder =
        ChatViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_conversation_item, parent, false)
        )

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {

        holder.apply {
            lastMsg!!.text = data[position].lastMessage
            name!!.text = data[position].otherName
            at!!.text = data[position].at

            val context = itemView.context

            usePicasso(
                data[position].otherImage,
                R.drawable.logonv,
                senderImage!!
            )
/*
            useSecurePicasso(
                data[position].otherImage,
                R.drawable.logonv,
                context,
                senderImage!!,
                OkHttp3Downloader(NetworkClient(context).secureHttpInterceptor()),
            )

 */
            itemView.setOnClickListener {
                val selected = data[position]
                selected.isRead = true
                data[position] = selected

                notifyItemChanged(position)

                Intent((it.context as HomeActivity), ChatRoomActivity::class.java).let { i ->
                    i.putExtra("OTHER_USERNAME", data[position].otherName)
                    startActivity((it.context as HomeActivity), i, null)
                }
            }
        }
    }

    override fun getItemCount(): Int = data.size
}