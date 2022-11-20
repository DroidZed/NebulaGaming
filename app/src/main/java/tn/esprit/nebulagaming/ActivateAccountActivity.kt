package tn.esprit.nebulagaming

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import tn.esprit.nebulagaming.viewmodels.VerifyAccountViewModel

class ActivateAccountActivity : AppCompatActivity() {

    private lateinit var timer: TextView
    private lateinit var codeInputLayout: TextInputLayout
    private lateinit var codeInputEditText: EditText
    private lateinit var buttonVerifValid: Button
    private lateinit var renvoyerCode: TextView

    private val verifAccVm: VerifyAccountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activate_account)

        renvoyerCode = findViewById(R.id.renvoyerCode)
        codeInputLayout = findViewById(R.id.codeInputLayout)
        codeInputEditText = findViewById(R.id.codeInputEditText)
        buttonVerifValid = findViewById(R.id.buttonverifValid)
        timer = findViewById(R.id.timeercountdown)

        timer.text = resources.getString(R.string.code_count_down)

        val emailUser: String = intent.getStringExtra("Email").toString()

        countDownTime()

        buttonVerifValid.setOnClickListener {
            val code = codeInputEditText.text.toString()
            if (code.isEmpty()) {
                codeInputLayout.error = "Code is required"
                codeInputEditText.requestFocus()
                return@setOnClickListener
            }
            if (countDownTime().toString() != "00:00") {
                if (code.length == 4) {
                    verifAccVm.handleVerifyAccountRequest(
                        this,
                        emailUser,
                        codeInputEditText,
                        codeInputLayout
                    )
                    verifAccVm.errorMessage.observe(this) { error ->
                        if (error != null) codeInputLayout.error = error
                        else {
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }
                    }
                }
            }
        }
    }

    private fun countDownTime() {
        object : CountDownTimer(3 * 60 * 1000.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                timer.text = resources.getString(R.string.timer_text, minutes, seconds)
            }

            override fun onFinish() {
                renvoyerCode.setOnClickListener {
                }
            }
        }.start()
    }
}