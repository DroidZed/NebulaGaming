package tn.esprit.nebulagaming.viewholders

import android.content.Intent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.makeramen.roundedimageview.RoundedImageView
import tn.esprit.apimodule.models.Product
import tn.esprit.nebulagaming.MarketplaceActivity
import tn.esprit.nebulagaming.ProductActivity
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.utils.HelperFunctions.usePicasso
import tn.esprit.shared.Consts.ID_PROD


class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var productDetailsOV: LinearLayout? = null
    private var productImage: RoundedImageView? = null
    private var productName: TextView? = null
    private var productDesc: TextView? = null
    private var productBtbMS: MaterialButton? = null

    init {

        productDetailsOV = itemView.findViewById(R.id.productDetailsOV)
        productImage = itemView.findViewById(R.id.productImage)
        productName = itemView.findViewById(R.id.productName)
        productDesc = itemView.findViewById(R.id.productDesc)
        productBtbMS = itemView.findViewById(R.id.productBtbMS)
    }


    fun bind(product: Product) {

        productImage?.setOnClickListener {

            productDetailsOV?.visibility = View.VISIBLE
        }

        productDetailsOV?.setOnClickListener {
            productDetailsOV?.visibility = View.INVISIBLE
        }

        usePicasso(
            url = product.image,
            placeholder = R.drawable.event_wallpaper,
            view = productImage!!
        )

        productName?.text = product.name
        productDesc?.text = product.description

        productBtbMS?.setOnClickListener {

            productDetailsOV?.visibility = View.INVISIBLE

            Intent(itemView.context as MarketplaceActivity, ProductActivity::class.java).let { i ->
                i.putExtra(ID_PROD, product._id)
                startActivity(itemView.context as MarketplaceActivity, i, null)
            }
        }
    }
}