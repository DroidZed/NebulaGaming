package tn.esprit.nebulagaming.viewholders

import android.content.Intent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.makeramen.roundedimageview.RoundedImageView
import tn.esprit.apimodule.models.Product
import tn.esprit.nebulagaming.MyProductsDetailsActivity
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.utils.HelperFunctions.getImageFromBackend
import tn.esprit.nebulagaming.utils.HelperFunctions.usePicasso
import tn.esprit.shared.Consts

class MyProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var myproductDetailsOV: LinearLayout? = null
    private var myproductImage: RoundedImageView? = null
    private var myproductName: TextView? = null
    private var myproductDesc: TextView? = null
    private var myproductBtbMS: MaterialButton? = null

    init {
        myproductDetailsOV = itemView.findViewById(R.id.myproductDetailsOV)
        myproductImage = itemView.findViewById(R.id.myproductImage)
        myproductName = itemView.findViewById(R.id.myproductName)
        myproductDesc = itemView.findViewById(R.id.myproductDesc)
        myproductBtbMS = itemView.findViewById(R.id.myproductBtbMS)
    }


    fun bind(product: Product) {

        myproductImage?.setOnClickListener {

            myproductDetailsOV?.visibility = View.VISIBLE
        }

        myproductDetailsOV?.setOnClickListener {
            myproductDetailsOV?.visibility = View.INVISIBLE
        }

        if (product.image != null)
            usePicasso(
                url = product.image!!,
                placeholder = R.drawable.event_wallpaper,
                view = myproductImage!!
            )

        usePicasso(
            getImageFromBackend(product.image!!),
            placeholder = R.drawable.event_wallpaper,
            myproductImage!!
        )
        myproductName?.text = product.name
        myproductDesc?.text = product.description
        myproductBtbMS?.setOnClickListener {

            myproductDetailsOV?.visibility = View.INVISIBLE

            val intent = Intent(itemView.context, MyProductsDetailsActivity::class.java)
            intent.putExtra(Consts.ID_PROD, product._id)
            ContextCompat.startActivity(itemView.context, intent, null)

        }
    }

}