package tn.esprit.nebulagaming.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import tn.esprit.apimodule.models.Article
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.viewholders.ArticleViewHolder


class ArticlesAdapter(val data: MutableList<Article>) :
    ClassicAdapter<ArticleViewHolder, Article>(data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
        ArticleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_article_item, parent, false)
        )

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) =
        holder.bind(data[position])

    override fun getItemCount(): Int = data.size

}