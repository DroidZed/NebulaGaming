package tn.esprit.nebulagaming.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.data.JobOffer
import tn.esprit.nebulagaming.viewholders.JobOffersViewHolder

class JobOffersAdapter(private val data: MutableList<JobOffer>) : Adapter<JobOffersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobOffersViewHolder =
        JobOffersViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.joboffer_item, parent, false)
        )

    override fun onBindViewHolder(holder: JobOffersViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}