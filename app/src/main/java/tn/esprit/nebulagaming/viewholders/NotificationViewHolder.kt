package tn.esprit.nebulagaming.viewholders

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import tn.esprit.nebulagaming.QuizActivity
import tn.esprit.nebulagaming.R
import tn.esprit.roommodule.models.Notification
import tn.esprit.shared.Consts.INTENT_QUIZ_ID

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

        itemView.setOnClickListener {

            when (notification.src) {

                "QUIZ" -> Intent(it.context, QuizActivity::class.java).let { i ->
                    i.putExtra(INTENT_QUIZ_ID, notification.data)
                    ContextCompat.startActivity(it.context, i, null)
                }

                "DM" -> {
                    /* TODO: open chat room and get the user id...this is for chat feature  */
                }
            }
        }
    }

}