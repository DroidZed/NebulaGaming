package tn.esprit.nebulagaming.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.viewholders.WishListViewHolder
import tn.esprit.roommodule.NebulaGamingDatabase
import tn.esprit.roommodule.models.Wishlist

class WishlistAdapter(private val list: MutableList<Wishlist>) :
    ClassicAdapter<WishListViewHolder, Wishlist>(list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListViewHolder =
        WishListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_one_wishlist, parent, false)
        )

    override fun onBindViewHolder(holder: WishListViewHolder, position: Int) {

        val wishlist = list[position]

        holder.bind(wishlist)

        holder.deleteWish?.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                NebulaGamingDatabase
                    .getInstance(holder.itemView.context)
                    .wishlistDao()
                    .delete(wishlist)
                withContext(Dispatchers.Main) { remove(wishlist) }
            }
        }
    }
}


