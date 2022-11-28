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
    private val VRGM: VerifyCodeFGPViewModel by viewModels()
    private lateinit var Email: String
    private lateinit var CodeTextLayout: TextInputLayout
    private lateinit var CodeInputText: TextInputEditText
    private lateinit var Buttonsub: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verifycodeforgetpass)

        CodeTextLayout = findViewById(R.id.codeInputLayoutfrgtpass)
        CodeInputText = findViewById(R.id.codeInputEditTextfrgtpass)
        Buttonsub = findViewById(R.id.buttonverifValidfrgtpass)
        Email = intent.getStringExtra("Email").toString()
        Buttonsub.setOnClickListener {
            val code = CodeInputText.text.toString()
            if (code.isEmpty()) {
                CodeTextLayout.error = "Code is required"
                CodeInputText.requestFocus()
                return@setOnClickListener
            }
            if (code.length == 4) {
                VRGM.handleVerifyCodeRequest(
                    this,
                    Email,
                    CodeInputText,
                    CodeTextLayout
                )
                VRGM.errorMessage.observe(this) { error ->
                    if (error != null) CodeTextLayout.error = error
                    else {

                        Intent(this, UnsecureChangePasswordActivity::class.java).apply {
                            putExtra("Email", Email)
                            startActivity(this)
                        }
                        finish()
                    }
                }
            } else {
                CodeTextLayout.error = "Code must be 4 digits"
                CodeInputText.requestFocus()
            }
        }

    }
}