package tn.esprit.nebulagaming.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.viewholders.NotificationViewHolder
import tn.esprit.roommodule.models.Notification

class NotificationsAdapter(private val data: MutableList<Notification>) :
    ClassicAdapter<NotificationViewHolder, Notification>(data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder =
        NotificationViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_notification_single, parent, false)
        )

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) =
        holder.bind(data[position])
}