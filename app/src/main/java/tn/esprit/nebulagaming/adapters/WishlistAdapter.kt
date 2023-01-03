package tn.esprit.nebulagaming.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import tn.esprit.nebulagaming.R
import tn.esprit.apimodule.models.Wishlist
import tn.esprit.nebulagaming.viewholders.WishListViewHolder

class WishlistAdapter(private val list: MutableList<Wishlist>) :
    ClassicAdapter<WishListViewHolder, Wishlist>(list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListViewHolder =
        WishListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_one_wishlist, parent, false)
        )

    override fun onBindViewHolder(holder: WishListViewHolder, position: Int) {

        val wishlist = list[position]
/*
        holder.idWish = wishlist.idWish
        holder.nomProduct?.text = wishlist.nameProduct
        holder.priceProduct?.text = wishlist.priceProduct
        holder.photoProduct?.setImageResource(wishlist.photoProduct)*/

        holder.deleteWish?.setOnClickListener { remove(wishlist) }
    }
}


