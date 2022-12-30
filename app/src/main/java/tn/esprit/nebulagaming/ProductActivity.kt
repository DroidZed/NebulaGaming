package tn.esprit.nebulagaming

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ProductActivity : AppCompatActivity() {
    private lateinit var CloseProduct: MaterialButton
    private lateinit var shareproductbtn: MaterialButton
    private lateinit var textInputLayoutproductname: TextInputLayout
    private lateinit var productname: TextInputEditText
    private lateinit var textInputLayout2productprice: TextInputLayout
    private lateinit var productprice: TextInputEditText
    private lateinit var textInputLayout3productdescription: TextInputLayout
    private lateinit var productdescription: TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
    }
}