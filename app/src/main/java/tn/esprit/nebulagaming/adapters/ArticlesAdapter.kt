package tn.esprit.nebulagaming.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.data.Article
import tn.esprit.nebulagaming.viewholders.ArticleViewHolder


class ArticlesAdapter (private val data: MutableList<Article>) :
    Adapter<ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
        ArticleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.article_item, parent, false)
        )

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    fun addAll(articles: List<Article>) {
        data.addAll(articles)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size


}