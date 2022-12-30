package tn.esprit.nebulagaming

import android.app.DatePickerDialog
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.nebulagaming.viewmodels.OffreJobViewModel
import java.util.*

@Suppress("DEPRECATION")
@AndroidEntryPoint
class NewJobOfferActivity : AppCompatActivity() {

    private lateinit var btnclose: Button
    private lateinit var Startdatebtnn: Button
    private lateinit var Enddatebtn: Button
    private lateinit var Startdatetext: TextView
    private lateinit var Enddatetext: TextView
    private lateinit var TitleOffreLay: TextInputLayout
    private lateinit var TitleOffreTf: TextInputEditText
    private lateinit var descOffreLay: TextInputLayout
    private lateinit var DescOffreTf: TextInputEditText
    private lateinit var sharepostbtn: Button
    private lateinit var AdressOffreLay: TextInputLayout
    private lateinit var AddressOffreTf: TextInputEditText
    private lateinit var PostionOffreLay: TextInputLayout
    private lateinit var PostionOffreTf: TextInputEditText
    private lateinit var WebsiteOffreLay: TextInputLayout
    private lateinit var WebsiteOffreTf: TextInputEditText
    private lateinit var EmailToOffreLay: TextInputLayout
    private lateinit var EmailToOffreTf: TextInputEditText
    private var imageUri: Uri? = null

    private lateinit var spin: Spinner

    private val Ofjvm: OffreJobViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_job_offer)

        spin = findViewById(R.id.typeOffrespin)
        val tpof = resources.getStringArray(R.array.offrejobType)
        TitleOffreLay = findViewById<View>(R.id.TitleOffreLay) as TextInputLayout
        TitleOffreTf = findViewById<View>(R.id.TitleOffreTf) as TextInputEditText
        descOffreLay = findViewById<View>(R.id.descOffreLay) as TextInputLayout
        DescOffreTf = findViewById<View>(R.id.DescOffreTf) as TextInputEditText
        sharepostbtn = findViewById<View>(R.id.sharepostbtn) as Button
        AdressOffreLay = findViewById<View>(R.id.AdressOffreLay) as TextInputLayout
        AddressOffreTf = findViewById<View>(R.id.AddressOffreTf) as TextInputEditText
        PostionOffreLay = findViewById<View>(R.id.PostionOffreLay) as TextInputLayout
        PostionOffreTf = findViewById<View>(R.id.PostionOffreTf) as TextInputEditText
        WebsiteOffreLay = findViewById<View>(R.id.WebsiteOffreLay) as TextInputLayout
        WebsiteOffreTf = findViewById<View>(R.id.WebsiteOffreTf) as TextInputEditText
        EmailToOffreLay = findViewById<View>(R.id.EmailToOffreLay) as TextInputLayout
        EmailToOffreTf = findViewById<View>(R.id.EmailToOffreTf) as TextInputEditText

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, tpof
        )
        spin.adapter = adapter

        btnclose = findViewById(R.id.Closepost)
        Startdatebtnn = findViewById(R.id.Startdatebtnn)
        Enddatebtn = findViewById(R.id.Enddatebtn)
        Startdatetext = findViewById(R.id.Startdatetext)
        Enddatetext = findViewById(R.id.EnddateText)

        btnclose.setOnClickListener {
            dial(spin)
        }

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        Startdatebtnn.setOnClickListener {
            val dpd = DatePickerDialog(
                this,
                { view, year, monthOfYear, dayOfMonth ->
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
                { view, year, monthOfYear, dayOfMonth ->
                    // Display Selected date in textbox
                    Enddatetext.setText("" + dayOfMonth + "/" + monthOfYear + "/" + year)
                },
                year,
                month,
                day
            )
            dpd.show()
        }

        sharepostbtn.setOnClickListener {
            val title = TitleOffreTf.text.toString()
            val desc = DescOffreTf.text.toString()
            val address = AddressOffreTf.text.toString()
            val startdate = Startdatetext.text.toString()
            val enddate = Enddatetext.text.toString()
            val type = spin.selectedItem.toString()
            val position = PostionOffreTf.text.toString()
            val website = WebsiteOffreTf.text.toString()
            val email = EmailToOffreTf.text.toString()

            if (title.isEmpty()) {
                TitleOffreLay.error = "Title is required"
                TitleOffreLay.requestFocus()
                return@setOnClickListener
            }
            if (desc.isEmpty()) {
                descOffreLay.error = "Description is required"
                descOffreLay.requestFocus()
                return@setOnClickListener
            }

            if (address.isEmpty()) {
                AdressOffreLay.error = "Address is required"
                AdressOffreLay.requestFocus()
                return@setOnClickListener
            }
            if (startdate.isEmpty()) {
                Toast.makeText(this, "Start date is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (enddate.isEmpty()) {
                Toast.makeText(this, "End date is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (type.isEmpty()) {
                Toast.makeText(this, "Type is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (position.isEmpty()) {
                PostionOffreLay.error = "Position is required"
                PostionOffreLay.requestFocus()
                return@setOnClickListener
            }
            if (website.isEmpty()) {
                WebsiteOffreLay.error = "Website is required"
                WebsiteOffreLay.requestFocus()
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                EmailToOffreLay.error = "Email is required"
                EmailToOffreLay.requestFocus()
                return@setOnClickListener
            }


            Ofjvm.handlesaveOffreJob(
                this,
                listOf(
                    TitleOffreTf,
                    DescOffreTf,
                    AddressOffreTf,
                    PostionOffreTf,
                    WebsiteOffreTf,
                    EmailToOffreTf
                ),
                listOf(
                    TitleOffreLay,
                    descOffreLay,
                    AdressOffreLay,
                    PostionOffreLay,
                    WebsiteOffreLay,
                    EmailToOffreLay
                ),
                startdate,
                enddate,
                type
            )
            Ofjvm.errorMessage.observe(this)
            {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
            Ofjvm.successMessage.observe(this)
            {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun dial(spin: Spinner) {
        if (TitleOffreTf.text.toString().isEmpty() && DescOffreTf.text.toString()
                .isEmpty() && Startdatetext.text.toString().isEmpty() && Enddatetext.text.toString()
                .isEmpty() && spin.selectedItem.toString().isEmpty() && imageUri.toString()
                .isEmpty()
        ) {
            finish()

        } else {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Alert")
            builder.setMessage("Are you sure you want to exit?")
            //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                Toast.makeText(
                    applicationContext,
                    android.R.string.yes, Toast.LENGTH_SHORT
                ).show()
                finish()
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->
                Toast.makeText(
                    applicationContext,
                    android.R.string.no, Toast.LENGTH_SHORT
                ).show()
            }

            builder.show()

        }
    }

}