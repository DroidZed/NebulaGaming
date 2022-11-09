package tn.esprit.nebulagaming

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import tn.esprit.nebulagaming.viewmodels.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private val loginVM: LoginViewModel by viewModels()

    private lateinit var emailET: EditText
    private lateinit var passwordET: EditText

    private lateinit var emailTL: TextInputLayout
    private lateinit var passwordTL: TextInputLayout

    private lateinit var loginBtn: Button

    private lateinit var forgotPasswordLink: TextView
    private lateinit var signUpPrompt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // findViewById
        emailET = findViewById(R.id.emailTf)
        passwordET = findViewById(R.id.passwordTf)

        emailTL = findViewById(R.id.emailTextLay)
        passwordTL = findViewById(R.id.passwordTextLay)

        loginBtn = findViewById(R.id.buttonLogin)

        forgotPasswordLink = findViewById(R.id.resetPasswordLink)
        signUpPrompt = findViewById(R.id.signUpText)

        // events
        loginBtn.setOnClickListener {
            loginVM.handleLogin(this, listOf(emailET, passwordET), listOf(emailTL, passwordTL))

            loginVM.loading.observe(this) { loadingValue ->

                if (!loadingValue) {

                    loginVM.errorMessage.observe(this) { error ->
                        if (error != null) {
                            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                        } else {
                            // startActivity(Intent(this, HomeScreen::class.java))
                            Toast.makeText(this, "Logged in !", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        forgotPasswordLink.setOnClickListener {
            startActivity(Intent(this, ForgetPasswordActivity::class.java))
        }

        signUpPrompt.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}