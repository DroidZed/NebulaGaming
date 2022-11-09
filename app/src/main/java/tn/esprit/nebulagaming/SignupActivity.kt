package tn.esprit.nebulagaming

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

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
                val intent = Intent(this, RoleActivity::class.java)
                startActivity(intent)
            }
        }

        haveacc.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }


}