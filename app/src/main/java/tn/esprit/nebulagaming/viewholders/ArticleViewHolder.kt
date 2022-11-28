package tn.esprit.nebulagaming.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import tn.esprit.apimodule.models.Article
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.utils.HelperFunctions.launchURL
import tn.esprit.nebulagaming.utils.HelperFunctions.toastMsg
import tn.esprit.nebulagaming.utils.HelperFunctions.usePicasso
import tn.esprit.roommodule.NebulaGamingDatabase
import tn.esprit.roommodule.models.Bookmarks

class ArticleViewHolder(itemView: View) : ViewHolder(itemView) {

    var articleTitle: TextView? = null
    var articleDescription: TextView? = null
    var articleImage: ImageView? = null
    var bookMarkImg: ImageView? = null
    var clickHere: ConstraintLayout? = null

    init {
        articleTitle = itemView.findViewById(R.id.artTitle)
        articleDescription = itemView.findViewById(R.id.artDescription)
        articleImage = itemView.findViewById(R.id.artImage)
        bookMarkImg = itemView.findViewById(R.id.bookMarkImg)
        clickHere = itemView.findViewById(R.id.clickHere)
    }

    fun bind(article: Article) {
        articleTitle!!.text = article.title
        articleDescription!!.text = article.content

        usePicasso(article.image!!, R.drawable.backg, articleImage!!)

        clickHere?.setOnClickListener {
            launchURL(it, article.link!!)
        }

        bookMarkImg?.setOnClickListener {


            try {
                NebulaGamingDatabase
                    .getInstance(itemView.context)
                    .bookmarksDao()
                    .create(
                        Bookmarks(
                            id = article.id!!,
                            title = article.title!!,
                            description = article.content!!,
                            image = article.image!!,
                            link = article.link!!
                        )
                    )
                toastMsg(itemView.context, "Added to bookmarks")
            } catch (e: Exception) {
                toastMsg(itemView.context, "Article already saved.")
            }


        }
    }
}