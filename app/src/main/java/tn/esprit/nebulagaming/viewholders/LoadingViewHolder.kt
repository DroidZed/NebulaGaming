package tn.esprit.nebulagaming.viewholders

import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import tn.esprit.nebulagaming.R

class LoadingViewHolder(itemView: View) : ViewHolder(itemView) {

    var progressBar: ProgressBar? = null

    init {
        progressBar = itemView.findViewById(R.id.progressBar)
    }
}