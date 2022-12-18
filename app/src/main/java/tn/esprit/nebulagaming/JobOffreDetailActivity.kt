package tn.esprit.nebulagaming

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.nebulagaming.utils.HelperFunctions
import tn.esprit.nebulagaming.utils.Status
import tn.esprit.nebulagaming.viewmodels.OffreJobViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class JobOffreDetailActivity : AppCompatActivity() {
    private lateinit var photoprofdetailuserDetOffrejob: ShapeableImageView
    private lateinit var usernamedetpostDetOffrejob: TextView
    private lateinit var datepostdetDetOffrejob: TextView
    private lateinit var textPostdetaiDetOffrejob: TextView
    private lateinit var detphotoforumDetOffrejob: ShapeableImageView
    private lateinit var titleJobDetOffrejob: TextView
    private lateinit var JobPostionDetOffrejob: TextView
    private lateinit var JobTypeDetOffrejob: TextView
    private lateinit var JobAdresseDetOffrejob: TextView
    private lateinit var datedebutDetOffrejob: TextView
    private lateinit var datefinDetOffrejob: TextView
    private lateinit var ApplyDetOffrejob: Button
    private lateinit var idofre: String
    private lateinit var EmailDetOffrejob: TextView
    private lateinit var CloseOffre : Button
    private val OfJVM: OffreJobViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_offre_detail)

        photoprofdetailuserDetOffrejob = findViewById(R.id.photoprofdetailuserDetOffrejob)
        usernamedetpostDetOffrejob = findViewById(R.id.usernamedetpostDetOffrejob)
        datepostdetDetOffrejob = findViewById(R.id.datepostdetDetOffrejob)
        textPostdetaiDetOffrejob = findViewById(R.id.textPostdetaiDetOffrejob)
        detphotoforumDetOffrejob = findViewById(R.id.detphotoforumDetOffrejob)
        titleJobDetOffrejob = findViewById(R.id.titleJobDetOffrejob)
        JobPostionDetOffrejob = findViewById(R.id.JobPostionDetOffrejob)
        JobTypeDetOffrejob = findViewById(R.id.JobTypeDetOffrejob)
        JobAdresseDetOffrejob = findViewById(R.id.JobAdresseDetOffrejob)
        datedebutDetOffrejob = findViewById(R.id.datedebutDetOffrejob)
        datefinDetOffrejob = findViewById(R.id.datefinDetOffrejob)
        ApplyDetOffrejob = findViewById(R.id.ApplyDetOffrejob)
        CloseOffre = findViewById(R.id.CloseOffre)
        EmailDetOffrejob = findViewById(R.id.EmailDetOffrejob)

        CloseOffre.setOnClickListener {
            finish()
        }
        //OfJVM loadOffreJobById


        idofre = intent.getStringExtra("jobid").toString()

        Log.d("Id offre de", idofre)

        OfJVM.loadOffreJobById(this, idofre).observe(this) {
            it?.let { rs ->
                when (rs.status) {
                    Status.SUCCESS -> {
                        rs.data?.let { data ->
                            titleJobDetOffrejob.text = data.jobTitle
                            JobTypeDetOffrejob.text = data.jobType
                            JobAdresseDetOffrejob.text = data.jobAdress
                            datedebutDetOffrejob.text = data.jobStartDate
                            datefinDetOffrejob.text = data.jobEndDate
                            textPostdetaiDetOffrejob.text = data.jobDescription
                            EmailDetOffrejob.text = data.jobEmail
                            usernamedetpostDetOffrejob.text = intent.getStringExtra("username").toString()
                            datepostdetDetOffrejob.text = parseDateToddMMyyyy(data.postedAt.toString())
                            JobPostionDetOffrejob.text = intent.getStringExtra("jobPostion").toString()
                            JobPostionDetOffrejob.text = data.jobPosition
                            Log.d("DATA", data.toString())
                            ApplyDetOffrejob.setOnClickListener {
                                HelperFunctions.launchURL(it, data.jobWebsite);

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
        }
    }
    private fun parseDateToddMMyyyy(time: String): String? {
        var inputPattern = "EEE MMM dd HH:mm:ss zzz yyyy"
        var outputPattern = "dd/MM/yyyy HH:mm"
        var inputFormat = SimpleDateFormat(inputPattern)
        var outputFormat = SimpleDateFormat(outputPattern)
        var date: Date? = null
        var str: String? = null
        try {
            date = inputFormat.parse(time)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return str
    }


}