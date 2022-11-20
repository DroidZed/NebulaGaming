package tn.esprit.nebulagaming.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.prof.rssparser.Parser
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.adapters.ArticlesAdapter
import tn.esprit.nebulagaming.data.Article
import tn.esprit.nebulagaming.viewmodels.ArticleViewModel

@AndroidEntryPoint
class ArticlesFragment : Fragment() {

    private val homeVM: ArticleViewModel by viewModels()

    private lateinit var rssParser: Parser

    private lateinit var articlesRV: RecyclerView

    private lateinit var swipeContainer: SwipeRefreshLayout

    private lateinit var articlesAdapter: ArticlesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_articles, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articlesRV = view.findViewById(R.id.articlesRV)

        swipeContainer = view.findViewById(R.id.swipeContainer)

        rssParser = Parser.Builder()
            .context(view.context)
            .cacheExpirationMillis(24L * 60L * 60L * 1000L) // one day
            .build()

        articlesAdapter = ArticlesAdapter(
            mutableListOf(
                Article(
                    title = "A1",
                    image = "https://avatars.githubusercontent.com/u/41507665?v=4",
                    description = "D1",
                    link = "https://droidzed.github.io"
                ),
                Article(
                    title = "A2",
                    image = "https://avatars.githubusercontent.com/u/41507665?v=4",
                    description = "D2",
                    link = "https://droidzed.github.io"
                ),
                Article(
                    title = "A3",
                    image = "https://avatars.githubusercontent.com/u/41507665?v=4",
                    description = "D3",
                    link = "https://droidzed.github.io"
                ),
                Article(
                    title = "A3",
                    image = "https://avatars.githubusercontent.com/u/41507665?v=4",
                    description = "D3",
                    link = "https://droidzed.github.io"
                ),
                Article(
                    title = "A3",
                    image = "https://avatars.githubusercontent.com/u/41507665?v=4",
                    description = "D3",
                    link = "https://droidzed.github.io"
                ),
            )
        )

        swipeContainer.setOnRefreshListener {

            // Your code to refresh the list here.

            // Make sure you call swipeContainer.setRefreshing(false)

            // once the network request has completed successfully.



            fetchTimelineAsync()

        }

        // Configure the refreshing colors

        swipeContainer.setColorSchemeResources(
            R.color.purple_500,
            R.color.DarkPurple,
        );

        articlesRV.apply {
            adapter = articlesAdapter
            layoutManager = LinearLayoutManager(view.context)
        }
    }


    private fun fetchTimelineAsync() {


        swipeContainer.isRefreshing = false


    }
}