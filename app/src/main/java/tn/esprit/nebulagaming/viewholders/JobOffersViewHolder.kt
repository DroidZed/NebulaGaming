package tn.esprit.nebulagaming.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import tn.esprit.apimodule.models.OffreJob
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.utils.HelperFunctions.launchURL

class JobOffersViewHolder(itemView: View) : ViewHolder(itemView) {

    private var jDesc: TextView? = null

    init {
        jDesc = itemView.findViewById(R.id.jDesc)
    }

    fun bind(jobOffer: OffreJob) {

        itemView.setOnClickListener {
            launchURL(it, jobOffer.jobWebsite)
        }

    }


}