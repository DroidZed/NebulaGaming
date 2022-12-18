package tn.esprit.nebulagaming.fragments

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.adapters.ArticlesAdapter
import tn.esprit.nebulagaming.utils.HelperFunctions.toastMsg
import tn.esprit.nebulagaming.utils.Status
import tn.esprit.nebulagaming.viewmodels.ArticleViewModel


@AndroidEntryPoint
class ArticlesFragment : Fragment(R.layout.fragment_articles) {

    private val articlesVM: ArticleViewModel by viewModels()

    private lateinit var articlesRV: RecyclerView

    private lateinit var swipeContainer: SwipeRefreshLayout

    private lateinit var noNetLayout: ConstraintLayout

    private lateinit var articlesLm: LinearLayoutManager
    private lateinit var articlesAdapter: ArticlesAdapter

    private var pageNumber: Int = 1
    private var pagedItemsCount: Int = 0
    private var totalItems: Int = 0
    private var totalPages: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articlesRV = view.findViewById(R.id.articlesRV)
        swipeContainer = view.findViewById(R.id.swipeContainer)
        noNetLayout = view.findViewById(R.id.noNetLayout)

        articlesAdapter = ArticlesAdapter(mutableListOf())
        articlesLm = LinearLayoutManager(view.context)

        articlesRV.apply {
            adapter = articlesAdapter
            layoutManager = articlesLm
        }

        loadData(pageNumber)

        swipeContainer.setOnRefreshListener {
            articlesAdapter.clear()
            loadData(pageNumber)
            articlesRV.refreshDrawableState()
        }

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(
            R.color.purple_500,
            R.color.DarkPurple,
        )
    }

    private fun loadData(page: Int) {
        articlesVM.loadRssArticles(page, view?.context!!).observe(requireActivity()) {
            it?.let { rs ->
                when (rs.status) {
                    Status.SUCCESS -> {
                        rs.data?.apply {
                            pagedItemsCount = this.pageSize
                            totalItems = this.total
                            totalPages = this.pages
                            pageNumber = this.page
                            swipeContainer.isRefreshing = false
                            articlesAdapter.addAll(this.articles)
                            articlesRV.smoothScrollToPosition(0)
                        }
                    }
                    Status.LOADING -> {
                        swipeContainer.isRefreshing = true
                    }
                    Status.ERROR -> {
                        swipeContainer.isRefreshing = false
                        toastMsg(view?.context!!, it.message!!)
                    }
                }
            }
        }
    }
}