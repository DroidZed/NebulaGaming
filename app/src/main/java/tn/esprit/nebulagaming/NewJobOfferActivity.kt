package tn.esprit.nebulagaming

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView
import java.util.*

@Suppress("DEPRECATION")
class NewJobOfferActivity : AppCompatActivity() {
    private lateinit var btnclose: Button
    private lateinit var Startdatebtnn: Button
    private lateinit var Enddatebtn: Button
    private lateinit var Startdatetext: TextView
    private lateinit var Enddatetext: TextView
    private lateinit var pickoffreimage: Button
    private lateinit var offreimage: ShapeableImageView
    val REQUEST_CODE = 100
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_job_offer)

        val spin = findViewById<View>(R.id.typeOffrespin) as Spinner
        val tpof = resources.getStringArray(R.array.offrejobType)
        pickoffreimage = findViewById<View>(R.id.pickoffreimage) as Button
        offreimage = findViewById<View>(R.id.offreimage) as ShapeableImageView


        pickoffreimage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_CODE)

        }



        if (spin != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, tpof
            )
            spin.adapter = adapter


        }

        btnclose = findViewById(R.id.Closepost)
        Startdatebtnn = findViewById(R.id.Startdatebtnn)
        Enddatebtn = findViewById(R.id.Enddatebtn)
        Startdatetext = findViewById(R.id.Startdatetext)
        Enddatetext = findViewById(R.id.EnddateText)

        btnclose.setOnClickListener {
            finish()
        }


        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        Startdatebtnn.setOnClickListener {
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    // Display Selected date in textbox
                    Startdatetext.setText("" + dayOfMonth + "/" + monthOfYear + "/" + year)
                },
                year,
                month,
                day
            )
            dpd.show()
        }
        Enddatebtn.setOnClickListener {
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    // Display Selected date in textbox
                    Enddatetext.setText("" + dayOfMonth + "/" + monthOfYear + "/" + year)
                },
                year,
                month,
                day
            )
            dpd.show()
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            offreimage.setImageURI(data?.data) // handle chosen image
        }
    }
}