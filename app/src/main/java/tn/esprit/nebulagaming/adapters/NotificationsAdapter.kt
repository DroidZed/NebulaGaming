package tn.esprit.nebulagaming.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.data.Notification
import tn.esprit.nebulagaming.viewholders.NotificationViewHolder

class NotificationsAdapter(private val data: MutableList<Notification>) :
    Adapter<NotificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder =
        NotificationViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.notification_single_layout, parent, false)
        )

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) =
        holder.bind(data[position])


    override fun getItemCount(): Int = data.size

}