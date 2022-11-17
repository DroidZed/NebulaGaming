package tn.esprit.nebulagaming.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.data.Notification
import tn.esprit.nebulagaming.utils.HelperFunctions.usePicasso

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
/*
        // TODO: add notification image from backend
        useSecurePicasso(
            notification.image,
            R.drawable.logonv,
            context,
            notifImage!!,
            OkHttp3Downloader(NetworkClient(itemView.context).secureHttpInterceptor())
        )
 */
        usePicasso(notification.image, R.drawable.logonv, notifImage!!)
    }

}