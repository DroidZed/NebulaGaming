package tn.esprit.nebulagaming

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import tn.esprit.nebulagaming.viewmodels.VerifyAccountViewModel

class ActivateaccountActivity : AppCompatActivity() {
    private lateinit var timer: TextView
    private lateinit var codeInputLayout: TextInputLayout
    private lateinit var codeInputEditText: EditText
    private lateinit var buttonverifValid : Button
    private lateinit var renvoyerCode:TextView
    private val VerifAccVm: VerifyAccountViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activateaccount)
        renvoyerCode=findViewById(R.id.renvoyerCode)
        codeInputLayout = findViewById(R.id.codeInputLayout)
        codeInputEditText = findViewById(R.id.codeInputEditText)
        buttonverifValid = findViewById(R.id.buttonverifValid)
        timer = findViewById(R.id.timeercountdown)
        timer.text = "3:00"
        val emailUser:String = intent.getStringExtra("Email").toString()

        //timer countdown
        val time = 3 * 60 * 1000.toLong()
        countdonwtime(time)


        buttonverifValid.setOnClickListener {
            val code = codeInputEditText.text.toString()
            if (code.isEmpty()) {
                codeInputLayout.error = "Code is required"
                codeInputEditText.requestFocus()
                return@setOnClickListener
            }
            if (countdonwtime(time).toString() != "00:00") {
                if (code.length == 4) {
                //    ProgressDialog.show(this@ActivateaccountActivity, "Loading", "Wait while loading...");


                    VerifAccVm.handleVerifyAccountRequest(
                        this@ActivateaccountActivity,
                        emailUser,
                        codeInputEditText,
                        codeInputLayout
                    )
                    VerifAccVm.loading.observe(this@ActivateaccountActivity) { loadingValue ->
                        ProgressDialog.show(this@ActivateaccountActivity, "Loading", "Wait while loading...");
                        if (!loadingValue) {
                            VerifAccVm.errorMessage.observe(this@ActivateaccountActivity) { error ->
                                if (error != null) {
                                        codeInputLayout.error = error

                                } else {
                                    val intent = Intent(this@ActivateaccountActivity, LoginActivity::class.java)
                                    startActivity(intent)
                                }
                            }
                        }
                    }
                }

            }

        }



    }

    private fun countdonwtime(time: Long) {
        object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                timer.text = "$minutes:$seconds"
            }

            override fun onFinish() {
                renvoyerCode.setOnClickListener {
                }
            }
        }.start()
    }
}