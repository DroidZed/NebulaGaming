package tn.esprit.nebulagaming

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.apimodule.models.Product
import tn.esprit.nebulagaming.utils.FileUtils
import tn.esprit.nebulagaming.utils.HelperFunctions
import tn.esprit.nebulagaming.utils.Status
import tn.esprit.nebulagaming.viewmodels.MarketplaceViewModel
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
    private lateinit var DoneUpload: TextView
    var idCategory: String? = null
    var idStatus: Int? = null
    var REQUEST_CODE_SELECT_PHOTO = 1
    var imageUri: Uri? = null

    private lateinit var progProddet: ProgressBar
    private val prodOfVm: MarketplaceViewModel by viewModels()
    private val prodVm: ProductViewModel by viewModels()
    private lateinit var idProduct: String

    @SuppressLint("MissingInflatedId")
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
        progProddet = findViewById(R.id.progProddet)
        DoneUpload = findViewById(R.id.DoneUpload)

        CloseProduct.setOnClickListener {
            finish()
        }

        idProduct = intent.getStringExtra(Consts.ID_PROD)!!
        Log.e("idProduct", idProduct)
        LoadData()

        // set spinner with mutablselist key value




        prodVm.loadCategories(this).observe(this) {

            it?.let { rs ->
                when (rs.status) {
                    Status.SUCCESS -> {
                        rs.data?.apply {

                            val adapter = ArrayAdapter(
                                this@MyproductsdetailsActivity,
                                android.R.layout.simple_spinner_item,
                                this.map { cat -> cat.name })
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                            myproducteditspinner.adapter = adapter
                        }
                    }
                    Status.LOADING -> {
                        Log.e("loadCategories", "LOADING")
                    }
                    Status.ERROR -> {

                        Log.e("OFFER-JOB", it.message!!)
                    }
                }
            }
        }


        addimageeditbtn2.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
            ) {
                // Permission is not granted, request it
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_CODE_SELECT_PHOTO
                )
            } else {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, REQUEST_CODE_SELECT_PHOTO)
            }
        }

        updatemyproducteditbtn.setOnClickListener {
            Log.e("idStatus f butn", idStatus.toString())
            if (imageUri == null) {
                progProddet.visibility = View.VISIBLE
                prodVm.updateproductwithoutimage(
                    this,
                    myproducteditname.text.toString(),
                    myproducteditdescription.text.toString(),
                    myproducteditprice.text.toString().toFloat(),
                    myproducteditquantity.text.toString().toInt(),
                    idCategory!!,
                    idProduct
                )
                prodVm.errorMessage.observe(this) {
                    if (it != null) {

                        progProddet.visibility = View.GONE
                        Intent(this, this@MyproductsdetailsActivity::class.java).also {
                            startActivity(it)
                        }
                    }
                }
            }
        }


        myproducteditspinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Log.e("ena Zero ", idCategory.toString())

                    prodVm.loadCategories(this@MyproductsdetailsActivity)
                        .observe(this@MyproductsdetailsActivity) {

                            it?.let { rs ->
                                when (rs.status) {
                                    Status.SUCCESS -> {
                                        rs.data?.apply {
                                            idCategory = this[position].name

                                            Log.e("ena loula ", idCategory.toString())
                                        }
                                    }
                                    Status.LOADING -> {
                                        Log.e("loadCategories", "LOADING")
                                    }
                                    Status.ERROR -> {

                                        Log.e("OFFER-JOB", it.message!!)
                                    }
                                }
                            }
                        }
                    Log.e("ena thanya", idCategory.toString())
                }


                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

        val group = listOf(
            myproducteditname,
            myproducteditprice,
            myproducteditdescription,
            myproducteditquantity,

            )

        disableComponents(group)

        editmyproductbtn.setOnClickListener { enableComponents(group) }

       /* updatemyproducteditbtn.setOnClickListener {
            disableComponents(group)
        }*/


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

        myproducteditspinner.visibility = View.GONE
        myproducteditspinner2.visibility = View.GONE
    }

    private fun enableComponents(group: List<TextView>) {
        group.forEach {
            it.isEnabled = true
        }
        updatemyproducteditbtn.visibility = View.VISIBLE
     //   addimageeditbtn2.visibility = View.VISIBLE
        myproducteditspinner.visibility = View.VISIBLE
       // myproducteditspinner2.visibility = View.VISIBLE
    }


    private fun LoadData() {
        prodOfVm.getProductById(this, idProduct).observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    progProddet.visibility = View.GONE
                    it.data?.let { product ->
                        setupUi(product)
                        Log.e("productname", product.name)
                        myproducttitle.text = product.name
                        myproducteditname.setText(product.name, TextView.BufferType.EDITABLE)

                        HelperFunctions.usePicasso(
                            HelperFunctions.getImageFromBackend(product.image!!),
                            R.drawable.logonv,
                            myproducteditimage
                        )
                        categorytext.text = product.category!!.name



                    }
                }
                Status.LOADING -> {
                    progProddet.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    progProddet.visibility = View.GONE
                    Log.e("Error", it.message!!)
                }
            }

        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_SELECT_PHOTO && resultCode == Activity.RESULT_OK && data != null) {
//progressBarUpimage with  green color


            DoneUpload.text = "Photo Uploaded"
            DoneUpload.setTextColor(resources.getColor(R.color.green))

            imageUri = data.data


            Log.e("imageUri", imageUri.toString())
        }
    }
}