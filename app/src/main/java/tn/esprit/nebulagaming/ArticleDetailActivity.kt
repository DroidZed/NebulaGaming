package tn.esprit.nebulagaming

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.apimodule.models.Article
import tn.esprit.nebulagaming.utils.HelperFunctions.usePicasso
import tn.esprit.nebulagaming.viewmodels.ArticleDetailViewModel
import tn.esprit.shared.Consts.ARTICLE_DETAIL

@AndroidEntryPoint
class ArticleDetailActivity : AppCompatActivity() {

    private val viewModel: ArticleDetailViewModel by viewModels()

    private lateinit var image: ImageView

    private lateinit var title: TextView
    private lateinit var content: TextView
    private lateinit var tagsIndicator: TextView

    private lateinit var chipGroup: ChipGroup

    private lateinit var articleTopBar: Toolbar

    private lateinit var checkArtBtn: Button

    private lateinit var shareBtn: ImageButton
    private lateinit var bookMarkImg: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        val article = intent.getParcelableExtra<Article>(ARTICLE_DETAIL)!!

        image = findViewById(R.id.image)
        title = findViewById(R.id.title)
        tagsIndicator = findViewById(R.id.tagsIndicator)
        content = findViewById(R.id.content)
        chipGroup = findViewById(R.id.chipGroup)
        checkArtBtn = findViewById(R.id.checkArtBtn)
        shareBtn = findViewById(R.id.shareBtn)
        bookMarkImg = findViewById(R.id.bookMarkImg)

        articleTopBar = findViewById(R.id.articleTopBar)

        setSupportActionBar(articleTopBar)

        articleTopBar.setNavigationOnClickListener { finish() }

        article.let { a ->

            usePicasso(
                a.image!!,
                R.drawable.event_wallpaper,
                image
            )

            title.text = a.title!!
            content.text = a.content!!

            if (a.tags != null) {

                tagsIndicator.visibility = View.VISIBLE

                a.tags?.forEach {
                    val chip = Chip(this)

                    chip.apply {
                        text = it
                        chipIcon = ResourcesCompat.getDrawable(
                            context.resources,
                            R.drawable.ic_baseline_tag_24,
                            null
                        )
                    }

                    chipGroup.addView(chip)
                }
            }

            checkArtBtn.setOnClickListener { viewModel.openArticlePage(this, a.link!!) }
            bookMarkImg.setOnClickListener { viewModel.addArticleToBookmarks(this, a) }
            shareBtn.setOnClickListener { viewModel.shareArticleWithPeople(this, a) }
        }
    }
}