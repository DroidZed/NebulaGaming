package tn.esprit.nebulagaming.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.adapters.WishlistAdapter
import tn.esprit.nebulagaming.models.Wishlist


class WishlistFragment : Fragment() {

    private lateinit var adapter: WishlistAdapter
    private lateinit var arraylist: ArrayList<Wishlist>
    private lateinit var recyclerView: RecyclerView
    lateinit var idWish: Array<Int>
    lateinit var photoProduct: Array<Int>
    lateinit var priceProduct: Array<String>
    lateinit var nomProduct: Array<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wishlist, container, false)
    }

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
        arraylist = arrayListOf<Wishlist>()
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
        for (i in photoProduct.indices) {
            arraylist.add(Wishlist(idWish[i], nomProduct[i], photoProduct[i], priceProduct[i]))
        }
    }
}