package tn.esprit.nebulagaming.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import tn.esprit.nebulagaming.R

class ArticleViewHolder(itemView: View) : ViewHolder(itemView) {

    var articleTitle: TextView? = null
    var articleDescription: TextView? = null
    var articleImage: ImageView? = null
    var bookMarkRem: ImageView? = null

    init {
        articleTitle = itemView.findViewById(R.id.artTitle)
        articleDescription = itemView.findViewById(R.id.artDescription)
        articleImage = itemView.findViewById(R.id.artImage)
        bookMarkRem = itemView.findViewById(R.id.bookMarkRem)
    }
}