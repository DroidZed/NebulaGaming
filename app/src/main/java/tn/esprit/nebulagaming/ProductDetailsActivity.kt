package tn.esprit.nebulagaming

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.nebulagaming.R.id.textInputLayout4productquantity
import tn.esprit.nebulagaming.viewmodels.ProductViewModel

@AndroidEntryPoint
class ProductDetailsActivity : AppCompatActivity() {

    private val productViewModel: ProductViewModel by viewModels()

    private lateinit var CloseProduct: MaterialButton
    private lateinit var shareproductbtn: MaterialButton
    private lateinit var textInputLayoutproductname: TextInputLayout
    private lateinit var productname: TextInputEditText
    private lateinit var textInputLayout2productprice: TextInputLayout
    private lateinit var productprice: TextInputEditText
    private lateinit var textInputLayout3productdescription: TextInputLayout
    private lateinit var productdescription: TextInputEditText
    private lateinit var textInputLayout4productquantity: TextInputLayout
    private lateinit var productquantity: TextInputEditText
    private lateinit var spinnerproduct: Spinner
    private lateinit var addimagebtn: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        CloseProduct = findViewById(R.id.CloseProduct)
        shareproductbtn = findViewById(R.id.shareproductbtn)
        textInputLayoutproductname = findViewById(R.id.textInputLayoutproductname)
        productname = findViewById(R.id.productname)
        textInputLayout2productprice = findViewById(R.id.textInputLayout2productprice)
        productprice = findViewById(R.id.productprice)
        textInputLayout3productdescription = findViewById(R.id.textInputLayout3productdescription)
        productdescription = findViewById(R.id.productdescription)
        textInputLayout4productquantity = findViewById(R.id.textInputLayout4productquantity)
        productquantity = findViewById(R.id.productquantity)
        spinnerproduct = findViewById(R.id.spinnerproduct)
        addimagebtn = findViewById(R.id.addimagebtn)

    }
}