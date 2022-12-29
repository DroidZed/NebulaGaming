package tn.esprit.nebulagaming.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import tn.esprit.apimodule.models.Article
import tn.esprit.nebulagaming.ArticleDetailActivity
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.utils.HelperFunctions
import tn.esprit.nebulagaming.utils.HelperFunctions.launchURL
import tn.esprit.nebulagaming.viewholders.ArticleViewHolder
import tn.esprit.shared.Consts.ARTICLE_DETAIL


class ArticlesAdapter constructor(val data: MutableList<Article>) :
    ClassicAdapter<ArticleViewHolder, Article>(data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
        ArticleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_article_item, parent, false)
        )

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

        val article = data[position]

        holder.apply {
            articleTitle!!.text = article.title
            articleDescription!!.text = article.content

            HelperFunctions.usePicasso(article.image!!, R.drawable.backg, articleImage!!)

            itemView.setOnClickListener {

                if (data[position].content.isNullOrEmpty()) launchURL(
                    itemView.context,
                    data[position].link!!
                )

                else Intent(it.context, ArticleDetailActivity::class.java).let { i ->
                    i.putExtra(ARTICLE_DETAIL, data[position])
                    startActivity(it.context, i, null)
                }
            }
        }
    }
}