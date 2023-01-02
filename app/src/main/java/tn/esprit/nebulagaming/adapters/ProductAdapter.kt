package tn.esprit.nebulagaming.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import tn.esprit.apimodule.models.Product
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.viewholders.ProductViewHolder

class ProductAdapter(private val data: MutableList<Product>) :
    ClassicAdapter<ProductViewHolder, Product>(data) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_product_item, parent, false)
        )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(data[position])

    fun filterProductsByCategory(category: String?) {

        var original = data

        clear()

        if (category.isNullOrEmpty()) addAll(original)

        original = original.asSequence().filter { p -> p.category?.name == category }.toMutableList()

        addAll(original)
    }

    fun filterProductsByName(name: String) {

        var original = data

        clear()

        original = original.asSequence().filter { p -> p.name == name }.toMutableList()

        addAll(original)
    }
}