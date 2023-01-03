package tn.esprit.nebulagaming.viewholders

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.utils.HelperFunctions.usePicasso
import tn.esprit.roommodule.models.Wishlist


class WishListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(wishlist: Wishlist) {
        nomProduct?.text = wishlist.name
        priceProduct?.text = itemView.context.getString(R.string.prod_price, wishlist.price)

        if (wishlist.image != null)

            usePicasso(
                wishlist.image!!,
                R.drawable.logonv,
                photoProduct!!
            )
    }

    var nomProduct: TextView? = null
    var priceProduct: TextView? = null
    var photoProduct: ImageView? = null
    var deleteWish: Button? = null

    init {
        nomProduct = itemView.findViewById(R.id.productname)
        priceProduct = itemView.findViewById(R.id.productprice)
        photoProduct = itemView.findViewById(R.id.imageViewprodcutwish)
        deleteWish = itemView.findViewById(R.id.deletbtn)
    }
}