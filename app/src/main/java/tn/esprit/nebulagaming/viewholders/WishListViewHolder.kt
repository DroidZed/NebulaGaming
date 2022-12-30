package tn.esprit.nebulagaming.viewholders

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.nebulagaming.R


class WishListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var idWish: Int? = 0
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