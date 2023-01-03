package tn.esprit.nebulagaming.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.adapters.WishlistAdapter
import tn.esprit.apimodule.models.Wishlist

@AndroidEntryPoint
class WishlistFragment : Fragment(R.layout.fragment_wishlist) {

    private lateinit var adapter: WishlistAdapter
    private lateinit var arraylist: ArrayList<Wishlist>
    private lateinit var recyclerView: RecyclerView
    private lateinit var idWish: Array<Int>
    private lateinit var photoProduct: Array<Int>
    private lateinit var priceProduct: Array<String>
    private lateinit var nomProduct: Array<String>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialise()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recycler_viewwishlist)
        recyclerView.layoutManager = layoutManager
        adapter = WishlistAdapter(arraylist)
        recyclerView.adapter = adapter
    }

    private fun dataInitialise() {
        arraylist = arrayListOf()
        idWish = arrayOf(1, 2, 3, 4, 5)
        photoProduct = arrayOf(
            R.drawable.goldreward,
            R.drawable.goldreward,
            R.drawable.goldreward,
            R.drawable.goldreward,
            R.drawable.goldreward
        )
        priceProduct = arrayOf("100", "100", "100", "100", "100")
        nomProduct = arrayOf(
            "Assassin's Creed",
            "Assassin's Creed",
            "Assassin's Creed",
            "Assassin's Creed",
            "Assassin's Creed"
        )
       /* for (i in photoProduct.indices) {
            arraylist.add(Wishlist(idWish[i], nomProduct[i], photoProduct[i], priceProduct[i]))
        }*/
    }
}