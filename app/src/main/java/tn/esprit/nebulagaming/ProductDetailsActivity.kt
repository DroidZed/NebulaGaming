package tn.esprit.nebulagaming

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.apimodule.models.Product
import tn.esprit.nebulagaming.fragments.BuyerInfoBottomSheet
import tn.esprit.nebulagaming.utils.HelperFunctions.getImageFromBackend
import tn.esprit.nebulagaming.utils.HelperFunctions.toastMsg
import tn.esprit.nebulagaming.utils.HelperFunctions.usePicasso
import tn.esprit.nebulagaming.viewmodels.MarketplaceViewModel
import tn.esprit.nebulagaming.viewmodels.WishListViewModel
import tn.esprit.roommodule.models.Wishlist
import tn.esprit.shared.Consts.ID_PROD

@AndroidEntryPoint
class ProductDetailsActivity : AppCompatActivity() {

    private val viewModel: MarketplaceViewModel by viewModels()
    private val wlViewModel: WishListViewModel by viewModels()

    private lateinit var productId: String

    private lateinit var productTopBar: Toolbar

    private lateinit var prodImg: ImageView
    private lateinit var prodCat: TextView
    private lateinit var prodName: TextView
    private lateinit var prodDesc: TextView
    private lateinit var prodPrice: TextView
    private lateinit var prodStockStatus: TextView
    private lateinit var addWishListBtn: Button
    private lateinit var contactBuyBtn: Button

    var product: Product? = null

    private lateinit var sheet: BuyerInfoBottomSheet

    private lateinit var progProd: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        productId = intent.getStringExtra(ID_PROD)!!

        productTopBar = findViewById(R.id.productTopBar)

        setSupportActionBar(productTopBar)

        productTopBar.setNavigationOnClickListener { finish() }

        prodImg = findViewById(R.id.prodImg)
        prodCat = findViewById(R.id.prodCat)
        prodName = findViewById(R.id.prodName)
        prodDesc = findViewById(R.id.prodDesc)
        prodPrice = findViewById(R.id.prodPrice)
        prodStockStatus = findViewById(R.id.prodStockStatus)
        addWishListBtn = findViewById(R.id.addWishListBtn)
        contactBuyBtn = findViewById(R.id.contactBuyBtn)
        progProd = findViewById(R.id.progProd)


        addWishListBtn.setOnClickListener { wlViewModel.add(product!!) }
        contactBuyBtn.setOnClickListener {

            sheet.show(supportFragmentManager, sheet.TAG)
        }

        progProd.visibility = View.VISIBLE

        viewModel.getProd(this, productId)

        setupUi()
    }

    private fun setupUi() {

        viewModel.productLv.observe(this) {

            if (it != null) {

                progProd.visibility = View.GONE

                it.apply {
                    sheet = BuyerInfoBottomSheet.new(publisher.email, publisher.phone)

                    product = this

                    usePicasso(
                        getImageFromBackend(this.image!!),
                        R.drawable.logonv,
                        prodImg
                    )
                    prodCat.let { t ->
                        {

                            if (this.category != null) t.text = this.category!!.name
                            else t.visibility = View.GONE
                        }
                    }
                    prodName.text = this.name
                    prodDesc.text = this.description
                    prodPrice.text = getString(R.string.prod_price, this.price)
                    prodStockStatus.text =
                        if (this.quantity > 0) "- In Stock Now -" else "- Out Of Stock -"
                }
            }
        }

    }
}