package tn.esprit.nebulagaming

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color.green
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
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.apimodule.models.Category
import tn.esprit.nebulagaming.utils.FileUtils
import tn.esprit.nebulagaming.utils.Status
import tn.esprit.nebulagaming.viewmodels.ProductViewModel

@AndroidEntryPoint
class AddProductActivity : AppCompatActivity() {
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
    private lateinit var progressBarUpimage: ProgressBar
    private lateinit var Done: TextView
    var idCategory: String ?? = null
    var REQUEST_CODE_SELECT_PHOTO = 1
    var imageUri: Uri? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

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
        progressBarUpimage = findViewById(R.id.progressBarUpimage)
        Done = findViewById(R.id.Done)

        CloseProduct.setOnClickListener {
            finish()
        }


        Log.e("loadCategories", productViewModel.loadCategories(this).toString())

        productViewModel.loadCategories(this).observe(this) {

            it?.let { rs ->
                when (rs.status) {
                    Status.SUCCESS -> {
                        rs.data?.apply {

                            val adapter = ArrayAdapter(
                                this@AddProductActivity,
                                android.R.layout.simple_spinner_item,
                                this.map {
                                    it.name

                                })
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                            spinnerproduct.adapter = adapter
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

        addimagebtn.setOnClickListener {

            progressBarUpimage.visibility = View.VISIBLE
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted, request it
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    1)
            }
            else {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, REQUEST_CODE_SELECT_PHOTO)
            }

        }


        spinnerproduct.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.e("ena Zero ", idCategory.toString())


                productViewModel.loadCategories(this@AddProductActivity).observe(this@AddProductActivity) {

                    it?.let { rs ->
                        when (rs.status) {
                            Status.SUCCESS -> {
                                rs.data?.apply {
                                    idCategory = this[position]._id
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
                idCategory = productViewModel.loadCategories(this@AddProductActivity).value?.data?.get(0)?._id
                Log.e("ena theltha", idCategory.toString())

            }
        }

        shareproductbtn.setOnClickListener {
            val name = productname.text.toString()
            val price = productprice.text.toString()
            val description = productdescription.text.toString()
            val quantity = productquantity.text.toString()
            if (name?.isEmpty()!!) {
                textInputLayoutproductname.error = "Please enter a name"
                return@setOnClickListener
            }
            if (price?.isEmpty()!!) {
                textInputLayout2productprice.error = "Please enter a price"
                return@setOnClickListener
            }
            if (description?.isEmpty()!!) {
                textInputLayout3productdescription.error = "Please enter a description"
                return@setOnClickListener
            }
            if (quantity?.isEmpty()!!) {
                textInputLayout4productquantity.error = "Please enter a quantity"
                return@setOnClickListener
            }
            if (imageUri == null) {
                addimagebtn.error = "Please add an image"
                return@setOnClickListener
            }
            val file = FileUtils.getFileFromUri(this, imageUri!!)

            productViewModel.handleSaveProduct(
                this,
                listOf(productname, productdescription, productprice, productquantity),
                listOf(textInputLayoutproductname, textInputLayout2productprice, textInputLayout3productdescription, textInputLayout4productquantity),
                idCategory!!,
                file!!
            )
            productViewModel.errorMessage.observe(this) {
                if (it != null) {
                    Done.text = it
                    Done.setTextColor(green(0))
                }
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_SELECT_PHOTO && resultCode == Activity.RESULT_OK && data != null) {
//progressBarUpimage with  green color
            
            progressBarUpimage.indeterminateDrawable.setColorFilter(
                resources.getColor(R.color.green),
                android.graphics.PorterDuff.Mode.MULTIPLY

            )
            Done.text = "Done"
            Done.setTextColor(resources.getColor(R.color.green))
            val selectedImageUri = data.data!!
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = contentResolver.query(selectedImageUri, filePathColumn, null, null, null)
            cursor?.moveToFirst()
            val columnIndex = cursor?.getColumnIndex(filePathColumn[0])!!
         imageUri =data?.data


            Log.e("imageUri", imageUri.toString())
            cursor.close()
        }
    }

}