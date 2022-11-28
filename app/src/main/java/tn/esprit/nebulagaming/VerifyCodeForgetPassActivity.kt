package tn.esprit.nebulagaming

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import tn.esprit.nebulagaming.viewmodels.VerifyCodeFGPViewModel

class VerifyCodeForgetPassActivity : AppCompatActivity() {
    private val vCodeViewModel: VerifyCodeFGPViewModel by viewModels()
    private lateinit var email: String
    private lateinit var codeTextLayout: TextInputLayout
    private lateinit var codeInputText: TextInputEditText
    private lateinit var buttonSubmit: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verifycodeforgetpass)

        codeTextLayout = findViewById(R.id.codeInputLayoutfrgtpass)
        codeInputText = findViewById(R.id.codeInputEditTextfrgtpass)
        buttonSubmit = findViewById(R.id.buttonverifValidfrgtpass)
        email = intent.getStringExtra("Email").toString()
        buttonSubmit.setOnClickListener {
            val code = codeInputText.text.toString()
            if (code.isEmpty()) {
                codeTextLayout.error = "Code is required"
                codeInputText.requestFocus()
                return@setOnClickListener
            }
            if (code.length == 4) {
                vCodeViewModel.handleVerifyCodeRequest(
                    this,
                    email,
                    codeInputText,
                    codeTextLayout
                )
                vCodeViewModel.errorMessage.observe(this) { error ->
                    if (error != null) codeTextLayout.error = error
                    else {

                        Intent(this, VerifyCodeForgetPassActivity::class.java).apply {
                            putExtra("Email", email)
                            startActivity(this)
                        }
                        finish()
                    }
                }
            } else {
                codeTextLayout.error = "Code must be 4 digits"
                codeInputText.requestFocus()
            }
        }

    }
}