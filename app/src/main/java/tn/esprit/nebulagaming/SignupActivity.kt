package tn.esprit.nebulagaming

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import tn.esprit.nebulagaming.utils.HelperFunctions.toastMsg
import tn.esprit.nebulagaming.utils.hideKeyboard
import tn.esprit.nebulagaming.utils.on
import tn.esprit.nebulagaming.viewmodels.RegisterViewModel

class SignupActivity : AppCompatActivity() {

    private lateinit var haveacc: TextView
    private lateinit var btnnext: Button
    private lateinit var NameTextLayout: TextInputLayout
    private lateinit var EmailTextLayout: TextInputLayout
    private lateinit var PasswordTextLayout: TextInputLayout
    private lateinit var PhoneTextLayout: TextInputLayout
    private lateinit var NameInputText: TextInputEditText
    private lateinit var EmailInputText: TextInputEditText
    private lateinit var PasswordInputText: TextInputEditText
    private lateinit var PhoneInputText: TextInputEditText

    private val RegVm: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        btnnext = findViewById(R.id.buttonSubmit)
        NameTextLayout = findViewById(R.id.nameTextLay)
        EmailTextLayout = findViewById(R.id.emailTextLay)
        PasswordTextLayout = findViewById(R.id.passwordTextLay)
        PhoneTextLayout = findViewById(R.id.phoneTextLay)
        NameInputText = findViewById(R.id.nameTf)
        EmailInputText = findViewById(R.id.emailTf)
        PasswordInputText = findViewById(R.id.passwordTf)
        PhoneInputText = findViewById(R.id.phoneTf)
        haveacc = findViewById(R.id.haveAccount)

        PhoneInputText.on(IME_ACTION_DONE) {
            PhoneInputText.apply {
                clearFocus()
                hideKeyboard()
            }
        }

        btnnext.setOnClickListener {
            if (NameInputText.text.toString().isEmpty()) {
                NameTextLayout.error = "Name Required"
            } else if (EmailInputText.text.toString().isEmpty()) {
                EmailTextLayout.error = "Email Required"
            } else if (PasswordInputText.text.toString().isEmpty()) {
                PasswordTextLayout.error = "Password Required"
            } else if (PhoneInputText.text.toString().isEmpty()) {
                PhoneTextLayout.error = "Phone Required"
            } else {
                RegVm.handleRegister(
                    this,
                    listOf(NameInputText, EmailInputText, PasswordInputText, PhoneInputText),
                    listOf(NameTextLayout, EmailTextLayout, PasswordTextLayout, PhoneTextLayout)
                )
                RegVm.errorMessage.observe(this) { error ->
                    if (error != null) {
                        when (error) {
                            "Email already exists" -> {
                                EmailTextLayout.error = error
                            }
                            "Phone already exists" -> {
                                PhoneTextLayout.error = error
                            }
                        }
                        toastMsg(this, error)
                    } else {
                        toastMsg(this, "Success in !")
                        Intent(this, ActivateAccountActivity::class.java).apply {
                            putExtra("Email", EmailInputText.text.toString())
                            startActivity(this)
                        }
                        finish()
                    }
                }


            }
        }

        haveacc.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }


}