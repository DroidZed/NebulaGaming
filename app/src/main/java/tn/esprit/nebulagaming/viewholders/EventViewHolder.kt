package tn.esprit.nebulagaming.viewholders

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.data.EventData

class EventViewHolder(itemView: View) : ViewHolder(itemView) {

    private var evDesc: TextView? = null
    private var evTitle: TextView? = null
    private var evTopic: TextView? = null
    private var evPeriod: TextView? = null

    init {

        evDesc = itemView.findViewById(R.id.evDesc)
        evTitle = itemView.findViewById(R.id.evTitle)
        evTopic = itemView.findViewById(R.id.evTopic)
        evPeriod = itemView.findViewById(R.id.evPeriod)
    }

    @SuppressLint("SetTextI18n")
    fun bind(e: EventData) {

        evDesc!!.text = e.description
        evTitle!!.text = e.title
        evTopic!!.text = e.topic
        evPeriod!!.text = "${e.startDate} - ${e.endDate}"
    }
}