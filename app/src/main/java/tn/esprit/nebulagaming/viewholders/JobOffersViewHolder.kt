package tn.esprit.nebulagaming.viewholders

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import tn.esprit.apimodule.models.OffreJob
import tn.esprit.nebulagaming.JobOffreDetailActivity
import tn.esprit.nebulagaming.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class JobOffersViewHolder(itemView: View) : ViewHolder(itemView) {

    // private var jDesc: TextView? = null
    private var nameuserpostOffreJob: TextView? = null

    //private var photoprofdetailuserOffreJob: ShapeableImageView? = null
    private var timepostOffreJob: TextView? = null
    private var jobTitle: TextView? = null
    private var typeJobOffre: TextView? = null
    private var PostionJob: TextView? = null
    private var descJobOffre: TextView? = null

    init {
        //   jDesc = itemView.findViewById(R.id.jDesc)
        nameuserpostOffreJob = itemView.findViewById(R.id.nameuserpostOffreJob)
        //  photoprofdetailuserOffreJob = itemView.findViewById(R.id.photoprofdetailuserOffreJob)
        timepostOffreJob = itemView.findViewById(R.id.timepostOffreJob)
        jobTitle = itemView.findViewById(R.id.JobTitleOffre)
        PostionJob = itemView.findViewById(R.id.PostionJob)
        descJobOffre = itemView.findViewById(R.id.descJobOffre)


    }

    fun bind(jobOffer: OffreJob) {
        jobTitle!!.text = jobOffer.jobTitle
        PostionJob!!.text = jobOffer.jobPosition
        timepostOffreJob!!.text = parseDateToddMMyyyy(jobOffer.postedAt.toString())
        nameuserpostOffreJob!!.text = jobOffer.company?.name.toString()
        descJobOffre!!.text = jobOffer.jobDescription

        itemView.setOnClickListener {
            val intent = Intent(itemView.context, JobOffreDetailActivity::class.java)
            intent.putExtra("jobid", jobOffer._id)
            intent.putExtra("jobPostion", jobOffer.jobPosition)
            intent.putExtra("username", jobOffer.company?.name.toString())
            itemView.context.startActivity(intent)
        }


    }

    //change date format
    private fun parseDateToddMMyyyy(time: String): String? {
        var inputPattern = "EEE MMM dd HH:mm:ss zzz yyyy"
        var outputPattern = "dd/MM/yyyy HH:mm"
        var inputFormat = SimpleDateFormat(inputPattern)
        var outputFormat = SimpleDateFormat(outputPattern)
        var date: Date? = null
        var str: String? = null
        try {
            date = inputFormat.parse(time)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return str
    }


}