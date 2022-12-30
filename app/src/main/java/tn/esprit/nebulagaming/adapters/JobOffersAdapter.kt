package tn.esprit.nebulagaming.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import tn.esprit.apimodule.models.OffreJob
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.viewholders.JobOffersViewHolder

class JobOffersAdapter(private val data: MutableList<OffreJob>) :
    ClassicAdapter<JobOffersViewHolder, OffreJob>(data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobOffersViewHolder =
        JobOffersViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_joboffer_item, parent, false)
        )

    override fun onBindViewHolder(holder: JobOffersViewHolder, position: Int) =
        holder.bind(data[position])
}
