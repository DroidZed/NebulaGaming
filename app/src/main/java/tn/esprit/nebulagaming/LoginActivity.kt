package tn.esprit.nebulagaming

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.nebulagaming.utils.clearText
import tn.esprit.nebulagaming.utils.hideKeyboard
import tn.esprit.nebulagaming.utils.on
import tn.esprit.nebulagaming.viewmodels.LoginViewModel


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val loginVM: LoginViewModel by viewModels()

    private lateinit var emailET: EditText
    private lateinit var passwordET: EditText

    private lateinit var emailTL: TextInputLayout
    private lateinit var passwordTL: TextInputLayout

    private lateinit var loginBtn: Button

    private lateinit var forgotPasswordLink: TextView
    private lateinit var signUpPrompt: TextView

    private lateinit var fbLoginBtn: MaterialButton
    private lateinit var googleBtn: MaterialButton
    private lateinit var discordButton: MaterialButton

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

        fbLoginBtn = findViewById(R.id.fbLoginBtn)
        googleBtn = findViewById(R.id.googleBtn)
        discordButton = findViewById(R.id.discordButton)

        passwordET.on(IME_ACTION_DONE) {
            passwordET.apply {
                clearFocus()
                hideKeyboard()
            }
        }

        // events
        loginBtn.setOnClickListener {
            loginVM.handleLogin(this, listOf(emailET, passwordET), listOf(emailTL, passwordTL))

            loginVM.errorMessage.observe(this) { error ->
                if (error != null)
                    Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                else {
                    listOf(emailET, passwordET).forEach { it.clearText() }
                    Toast.makeText(this, "Logged in !", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
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