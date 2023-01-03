package tn.esprit.nebulagaming.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.adapters.WishlistAdapter
import tn.esprit.nebulagaming.viewmodels.WishListViewModel

@AndroidEntryPoint
class WishlistFragment : Fragment(R.layout.fragment_wishlist) {

    private val viewModel: WishListViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_viewwishlist)

        val data = viewModel.getAll()

        val wishListItems = if (data != null) data.wishlistItems?.toMutableList() else  mutableListOf()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = WishlistAdapter(wishListItems!!)
        }
    }
}