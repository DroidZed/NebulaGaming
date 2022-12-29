package tn.esprit.nebulagaming.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
            h.bookMarkRem?.apply {
                visibility = View.VISIBLE
                setImageResource(R.drawable.ic_baseline_bookmark_remove_24)
            }

            usePicasso(bookmark.image, R.drawable.backg, h.articleImage!!)

            h.itemView.setOnClickListener {
                HelperFunctions.launchURL(it.context, bookmark.link)
            }

            h.bookMarkRem?.setOnClickListener {

                CoroutineScope(Dispatchers.IO).launch {
                    NebulaGamingDatabase
                        .getInstance(h.itemView.context)
                        .bookmarksDao()
                        .delete(bookmark)
                    withContext(Dispatchers.Main) { remove(bookmark) }
                }
            }
        }
    }
}
