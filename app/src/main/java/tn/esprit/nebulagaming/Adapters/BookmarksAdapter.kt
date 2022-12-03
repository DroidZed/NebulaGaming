package tn.esprit.nebulagaming.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.utils.HelperFunctions
import tn.esprit.nebulagaming.utils.HelperFunctions.usePicasso
import tn.esprit.nebulagaming.viewholders.ArticleViewHolder
import tn.esprit.roommodule.NebulaGamingDatabase
import tn.esprit.roommodule.models.Bookmarks

class BookmarksAdapter(private val list: MutableList<Bookmarks>) :
    ClassicAdapter<ArticleViewHolder, Bookmarks>(list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
        ArticleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_article_item, parent, false)
        )

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

        val bookmark = list[position]

        holder.let { h ->
            h.articleTitle!!.text = bookmark.title
            h.articleDescription!!.text = bookmark.description
            h.bookMarkImg?.setImageResource(R.drawable.ic_baseline_bookmark_remove_24)

            usePicasso(bookmark.image, R.drawable.backg, h.articleImage!!)

            h.clickHere?.setOnClickListener {
                HelperFunctions.launchURL(it, bookmark.link)
            }

            h.bookMarkImg?.setOnClickListener {

                NebulaGamingDatabase
                    .getInstance(h.itemView.context)
                    .bookmarksDao()
                    .delete(bookmark)

                remove(bookmark)
            }
        }
    }

    override fun getItemCount(): Int = list.size
}