package tn.esprit.nebulagaming.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import tn.esprit.apimodule.models.OffreJob
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.viewholders.JobOffersViewHolder

class JobOffersAdapter(private val data: MutableList<OffreJob>) : Adapter<JobOffersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobOffersViewHolder =
        JobOffersViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_joboffer_item, parent, false)
        )

    override fun onBindViewHolder(holder: JobOffersViewHolder, position: Int) =
        holder.bind(data[position])

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    fun addAll(offers: MutableList<OffreJob>) {
        data.addAll(offers)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size
}