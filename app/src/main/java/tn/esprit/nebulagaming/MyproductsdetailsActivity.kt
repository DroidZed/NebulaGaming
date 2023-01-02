package tn.esprit.nebulagaming

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.apimodule.models.Product
import tn.esprit.nebulagaming.utils.Status
import tn.esprit.nebulagaming.viewmodels.ProductViewModel
import tn.esprit.shared.Consts
@AndroidEntryPoint
class MyproductsdetailsActivity : AppCompatActivity() {
    private lateinit var CloseProduct: MaterialButton
    private lateinit var myproducttitle: TextView
    private lateinit var editmyproductbtn: MaterialButton
    private lateinit var myproducteditimage: ImageView
    private lateinit var myproducteditname: EditText
    private lateinit var myproducteditprice: EditText
    private lateinit var myproducteditdescription: EditText
    private lateinit var myproducteditquantity: EditText
    private lateinit var myproducteditspinner: Spinner
    private lateinit var myproducteditspinner2: Spinner
    private lateinit var updatemyproducteditbtn: MaterialButton
    private lateinit var addimageeditbtn2: MaterialButton
    private lateinit var categorytext: TextView
    private lateinit var statustext: TextView
    private val prodOfVm: ProductViewModel by viewModels()
    var idProduct: String?? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myproductsdetails)
        CloseProduct = findViewById(R.id.CloseProduct)
        myproducttitle = findViewById(R.id.myproducttitle)
        editmyproductbtn = findViewById(R.id.editmyproductbtn)
        myproducteditimage = findViewById(R.id.myproducteditimage)
        myproducteditname = findViewById(R.id.myproducteditname)
        myproducteditprice = findViewById(R.id.myproducteditprice)
        myproducteditdescription = findViewById(R.id.myproducteditdescription)
        myproducteditquantity = findViewById(R.id.myproducteditquantity)
        myproducteditspinner = findViewById(R.id.myproducteditspinner)
        myproducteditspinner2 = findViewById(R.id.myproducteditspinner2)
        updatemyproducteditbtn = findViewById(R.id.updatemyproducteditbtn)
        addimageeditbtn2 = findViewById(R.id.addimageeditbtn2)
        categorytext = findViewById(R.id.categorytext)
        statustext = findViewById(R.id.statustext)

        CloseProduct.setOnClickListener {
            finish()
        }

        idProduct = intent.getStringExtra(Consts.ID_PROD).toString()!!
        Log.e("idProduct", idProduct!!)

       /* prodOfVm.loadProductbyId(this, idProduct!!).observe(this) {
            it?.let { rs ->
                when (rs.status) {
                    Status.SUCCESS -> {
                        rs.data?.let { product ->
                            Log.e("product", product.toString())
                            myproducttitle.text = product.name
                            myproducteditname.setText(product.name)
                            myproducteditprice.setText(product.price.toString())
                            myproducteditdescription.setText(product.description)
                            myproducteditquantity.setText(product.quantity.toString())
                            categorytext.text = product.category.toString()
                            if (product.status == 1) {
                                statustext.text = "On Stock"
                            } else {
                                statustext.text = "Out of Stock"
                            }

                        }
                    }
                    Status.ERROR -> {
                        Log.e("DATA", rs.message!!)
                    }
                    Status.LOADING -> {
                        //HelperFunctions.toastMsg(context, "Loading")
                    }

                }

            }
        }*/
        val group = listOf(
            myproducteditname,
            myproducteditprice,
            myproducteditdescription,
            myproducteditquantity,

            )

        disableComponents(group)

        editmyproductbtn.setOnClickListener {
            enableComponents(group)
        }

        updatemyproducteditbtn.setOnClickListener {
            disableComponents(group)
        }


    }


    private fun setupUi(product: Product) {
        myproducttitle.text = product.name
        myproducteditname.setText(product.name, TextView.BufferType.EDITABLE)
        myproducteditprice.setText(product.price.toString(), TextView.BufferType.EDITABLE)
        myproducteditdescription.setText(product.description, TextView.BufferType.EDITABLE)
        myproducteditquantity.setText(product.quantity.toString(), TextView.BufferType.EDITABLE)
    }

    private fun disableComponents(group: List<TextView>) {
        group.forEach {
            it.isEnabled = false
        }
        updatemyproducteditbtn.visibility = View.GONE
        addimageeditbtn2.visibility = View.GONE
        myproducteditspinner.isEnabled = false
        myproducteditspinner2.isEnabled = false
        myproducteditspinner.isClickable = false
        myproducteditspinner2.isClickable = false
    }

    private fun enableComponents(group: List<TextView>) {
        group.forEach {
            it.isEnabled = true
        }
        updatemyproducteditbtn.visibility = View.VISIBLE
        addimageeditbtn2.visibility = View.VISIBLE
        myproducteditspinner.isEnabled = true
        myproducteditspinner2.isEnabled = true
        myproducteditspinner.isClickable = true
        myproducteditspinner2.isClickable = true
    }
}