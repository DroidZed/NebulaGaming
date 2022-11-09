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
    private lateinit var signUpBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // findViewById

        // events
        loginBtn.setOnClickListener {
            loginVM.handleLogin(listOf(emailET, passwordET), listOf(emailTL, passwordTL))

            loginVM.errorMessage.observe(this) {
                if (it != null) {
                    Toast.makeText(this, "Error logging in", Toast.LENGTH_SHORT).show()
                } else {
                    // startActivity(Intent(this, HomeScreen::class.java))
                }
            }
        }

        forgotPasswordLink.setOnClickListener {
            startActivity(Intent(this, ForgetPasswordActivity::class.java))
        }

        signUpBtn.setOnClickListener {
            //TODO: generate SignUp activity startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}