package tn.esprit.nebulagaming.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import tn.esprit.apimodule.models.Event
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.viewholders.EventViewHolder

class EventsAdapter(private val data: MutableList<Event>) :
    ClassicAdapter<EventViewHolder, Event>(data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder =
        EventViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_event_item, parent, false)
        )

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) =
        holder.bind(data[position])
}