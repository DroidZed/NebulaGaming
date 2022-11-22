package tn.esprit.nebulagaming

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import tn.esprit.nebulagaming.viewmodels.UnsecurechangepassViewModel

class UnsecureChangepasswordActivity : AppCompatActivity() {
    private lateinit var passwordTextLaychngpass: TextInputLayout
    private lateinit var passwordTfchngpass: TextInputEditText
    private lateinit var buttonverifValidfrgtpass: Button
    private lateinit var Email: String

    private val UnsecVM : UnsecurechangepassViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unsecure_changepassword)

        passwordTextLaychngpass = findViewById(R.id.passwordTextLaychngpass)
        passwordTfchngpass = findViewById(R.id.passwordTfchngpass)
        buttonverifValidfrgtpass = findViewById(R.id.buttonverifValidfrgtpass)
        Email=intent.getStringExtra("Email").toString()

        buttonverifValidfrgtpass.setOnClickListener {
            val password = passwordTfchngpass.text.toString()
            if (password.isEmpty()) {
                passwordTextLaychngpass.error = "Password is required"
                passwordTfchngpass.requestFocus()
                return@setOnClickListener
            }
            if (password.length < 8) {
                passwordTextLaychngpass.error = "Password must be at least 8 characters"
                passwordTfchngpass.requestFocus()
                return@setOnClickListener
            }
            UnsecVM.handleUnsecureChangePassRequest(
                this,
                Email,
                passwordTfchngpass,
                passwordTextLaychngpass
            )
            UnsecVM.errorMessage.observe(this) { error ->
                if (error != null) passwordTextLaychngpass.error = error
                else {
                    Intent(this, LoginActivity::class.java).apply {
                        startActivity(this)
                    }
                }
            }
        }


    }
}