package tn.esprit.nebulagaming.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.data.ProductDetails
import tn.esprit.nebulagaming.viewholders.ProductViewHolder

class ProductAdapter(private val data: MutableList<ProductDetails>) : Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_product_item, parent, false)
        )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(data[position])

    override fun getItemCount(): Int = data.size
}