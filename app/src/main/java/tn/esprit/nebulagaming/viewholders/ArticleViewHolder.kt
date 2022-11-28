package tn.esprit.nebulagaming.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import tn.esprit.apimodule.models.Article
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.utils.HelperFunctions.launchURL
import tn.esprit.nebulagaming.utils.HelperFunctions.usePicasso

class ArticleViewHolder(itemView: View) : ViewHolder(itemView) {

    var articleTitle: TextView? = null
    var articleDescription: TextView? = null
    var articleImage: ImageView? = null
    var bookMarkImg: ImageView? = null

    init {

        articleTitle = itemView.findViewById(R.id.artTitle)
        articleDescription = itemView.findViewById(R.id.artDescription)
        articleImage = itemView.findViewById(R.id.artImage)
        bookMarkImg = itemView.findViewById(R.id.bookMarkImg)

    }

    fun bind(article: Article) {
        articleTitle!!.text = article.title
        articleDescription!!.text = article.content

        usePicasso(article.image!!, R.drawable.backg, articleImage!!)

        articleImage?.setOnClickListener {
            launchURL(it, article.link!!)
        }
    }


}