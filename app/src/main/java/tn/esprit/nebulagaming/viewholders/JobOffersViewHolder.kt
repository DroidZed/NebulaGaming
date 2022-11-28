package tn.esprit.nebulagaming.viewholders

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import tn.esprit.apimodule.models.OffreJob
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.data.JobOffer
import tn.esprit.nebulagaming.utils.HelperFunctions.launchURL

class JobOffersViewHolder(itemView: View) : ViewHolder(itemView) {

    private var jDesc: TextView? = null

    init {
        jDesc = itemView.findViewById(R.id.jDesc)
    }

    fun bind(jobOffer: OffreJob) {

        jDesc?.text = itemView.resources
            .getString(
                R.string.job_description,
                jobOffer.jobTitle,
                jobOffer.jobType,
                jobOffer.jobDescription,
                jobOffer.jobAdress,
                jobOffer.jobSalary,
                jobOffer.jobStartDate,

            )
/*
        itemView.setOnClickListener {
            launchURL(it, jobOffer.link)
        }
*/
    }


}