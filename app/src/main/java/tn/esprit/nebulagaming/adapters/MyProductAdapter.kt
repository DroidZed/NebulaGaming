package tn.esprit.nebulagaming.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import tn.esprit.apimodule.models.Product
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.viewholders.MyProductViewHolder


class MyProductAdapter(private val data: MutableList<Product>) :
    ClassicAdapter<MyProductViewHolder, Product>(data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyProductViewHolder =
        MyProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_myproduct_item, parent, false)
        )


    override fun onBindViewHolder(holder: MyProductViewHolder, position: Int) =
        holder.bind(data[position])





}