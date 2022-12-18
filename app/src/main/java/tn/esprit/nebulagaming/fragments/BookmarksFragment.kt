package tn.esprit.nebulagaming.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.apimodule.models.Article
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.adapters.BookmarksAdapter
import tn.esprit.nebulagaming.viewmodels.BookmarksViewModel
import tn.esprit.roommodule.models.Bookmarks


@AndroidEntryPoint
class BookmarksFragment : Fragment(R.layout.fragment_bookmarks) {

    private val bkViewModel: BookmarksViewModel by viewModels()

    private lateinit var bookmarksRV: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookmarksRV = view.findViewById(R.id.bookmarksRV)

        val data = bkViewModel.loadBookmarkedArticles()

        val bookmarks = if (data != null) data.bookmarks?.toMutableList() else mutableListOf()

        bookmarksRV.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter =
                BookmarksAdapter(bookmarks!!)
        }
    }
}