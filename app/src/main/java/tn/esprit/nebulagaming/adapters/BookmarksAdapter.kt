package tn.esprit.nebulagaming.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.utils.HelperFunctions
import tn.esprit.nebulagaming.viewholders.ArticleViewHolder
import tn.esprit.roommodule.NebulaGamingDatabase
import tn.esprit.roommodule.models.Bookmarks

class BookmarksAdapter(private val list: MutableList<Bookmarks>) : Adapter<ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
        ArticleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_article_item, parent, false)
        )

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

        holder.let { h ->
            h.articleTitle!!.text = list[position].title
            h.articleDescription!!.text = list[position].description

            h.bookMarkImg?.setImageResource(R.drawable.ic_baseline_bookmark_remove_24)

            HelperFunctions.usePicasso(list[position].image, R.drawable.backg, h.articleImage!!)

            h.clickHere?.setOnClickListener {
                HelperFunctions.launchURL(it, list[position].link)
            }

            h.bookMarkImg?.setOnClickListener {

                NebulaGamingDatabase
                    .getInstance(h.itemView.context)
                    .bookmarksDao()
                    .delete(list[position])

                list.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }

    override fun getItemCount(): Int = list.size
}