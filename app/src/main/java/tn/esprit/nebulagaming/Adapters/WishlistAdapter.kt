package tn.esprit.nebulagaming.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.data.Wishlist

class WishlistAdapter(
    private val list: ArrayList<Wishlist>,
) : RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_one_wishlist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wishlist = list[position]

        holder.idWish = wishlist.idWish
        holder.nomProdcut.text = wishlist.nameProduct
        holder.priceProduct.text = wishlist.priceProduct
        holder.photoProduct.setImageResource(wishlist.photoProduct)
        holder.deleteWish.setOnClickListener {
            list.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, list.size)
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var idWish = 0
        val nomProdcut = itemView.findViewById<TextView>(R.id.productname)
        val priceProduct = itemView.findViewById<TextView>(R.id.productprice)
        val photoProduct = itemView.findViewById<ImageView>(R.id.imageViewprodcutwish)
        val deleteWish = itemView.findViewById<Button>(R.id.deletbtn)
    }
}


