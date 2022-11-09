package tn.esprit.nebulagaming

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Signin : AppCompatActivity() {
    private lateinit var btnLogin: Button
    private lateinit var dnthave: TextView
    private lateinit var resettext:TextView
    private lateinit var EmailTextLayout: TextInputLayout
    private lateinit var PasswordTextLayout: TextInputLayout
    private lateinit var EmailInputText: TextInputEditText
    private lateinit var PasswordInputText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        EmailTextLayout = findViewById(R.id.emailTextLay)
        PasswordTextLayout = findViewById(R.id.passwordTextLay)
        EmailInputText = findViewById(R.id.emailTf)
        PasswordInputText = findViewById(R.id.passwordTf)
        btnLogin = findViewById(R.id.buttonLogin)
        dnthave = findViewById(R.id.dnthaveAccount)
        resettext = findViewById(R.id.resetpassword)


        btnLogin.setOnClickListener {
            if (EmailInputText.text.toString().isEmpty()) {
                EmailTextLayout.error = "Email Required"
            } else if (PasswordInputText.text.toString().isEmpty()) {
                PasswordTextLayout.error = "Password Required"
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

        resettext.setOnClickListener {
            val intent = Intent(this, Resetpasswordemail::class.java)
            startActivity(intent)
        }
        dnthave.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

    }
}