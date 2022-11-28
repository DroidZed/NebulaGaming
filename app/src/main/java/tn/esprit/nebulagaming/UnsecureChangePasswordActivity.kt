package tn.esprit.nebulagaming

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import com.google.android.material.textfield.TextInputLayout
import tn.esprit.nebulagaming.viewmodels.UnsecureChangePassViewModel

class UnsecureChangePasswordActivity : AppCompatActivity() {
    private lateinit var Passwordinput: EditText
    private lateinit var PasswordLayout: TextInputLayout
    private lateinit var emailuser: TextView
    private lateinit var btnsubm: Button
    private lateinit var Email: String
    private val VmUnsecure: UnsecureChangePassViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unsecure_changepassword)
        Passwordinput = findViewById(R.id.passwordTfchngpass)
        PasswordLayout = findViewById(R.id.passwordTextLaychngpass)
btnsubm = findViewById(R.id.buttonverifValidfrgtpass)
        Email=intent.getStringExtra("Email").toString()
        emailuser = findViewById(R.id.emailuserchangepass)
        emailuser.text= Email

        btnsubm.setOnClickListener{
            val password = Passwordinput.text.toString()
            if (password.isEmpty()) {
                PasswordLayout.error = "Password is required"
                Passwordinput.requestFocus()
                return@setOnClickListener
            }
            if (password.length < 8) {
                PasswordLayout.error = "Password must be at least 8 characters"
                Passwordinput.requestFocus()
                return@setOnClickListener
            }
            VmUnsecure.handleUnsecureChangePassRequest(
                this,
                Email,
                Passwordinput,
                PasswordLayout
            )
            VmUnsecure.errorMessage.observe(this) { error ->
                if (error != null) PasswordLayout.error = error
                else {
                    Intent(this, LoginActivity::class.java).apply {
                        startActivity(this)
                    }
                    finish()
                }
            }
        }

    }
}