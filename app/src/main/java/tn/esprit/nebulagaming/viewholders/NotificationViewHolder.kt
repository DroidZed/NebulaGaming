package tn.esprit.nebulagaming.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.data.Notification

class NotificationViewHolder(itemView: View) : ViewHolder(itemView) {

    private var notifImage: ImageView? = null
    private var notifTitle: TextView? = null
    private var notifBody: TextView? = null

    init {
        notifImage = itemView.findViewById(R.id.notifImage)
        notifTitle = itemView.findViewById(R.id.notifTitle)
        notifBody = itemView.findViewById(R.id.notifBody)
    }


    fun bind(notification: Notification) {
        notifTitle!!.text = notification.title
        notifBody!!.text = notification.body

       Picasso.get()
            .load(notification.image)
            .placeholder(R.drawable.logonv)
            .into(notifImage)

    }
}