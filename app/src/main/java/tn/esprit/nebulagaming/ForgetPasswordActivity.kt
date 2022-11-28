package tn.esprit.nebulagaming

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.nebulagaming.utils.hideKeyboard
import tn.esprit.nebulagaming.utils.on
import tn.esprit.nebulagaming.viewmodels.ForgetPasswordViewModel

@AndroidEntryPoint
class ForgetPasswordActivity : AppCompatActivity() {

    private val forgetPwdVM: ForgetPasswordViewModel by viewModels()

     private lateinit var emailInput: EditText

     private lateinit var emailTLayout: TextInputLayout

    private lateinit var actionBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        emailInput = findViewById(R.id.codeInputEditTextforgtpass)
        emailTLayout = findViewById(R.id.emailforgtpasslayout)
        actionBtn = findViewById(R.id.buttonverifValidfrgtpass)
        // findViewById


        emailInput.on(EditorInfo.IME_ACTION_DONE) {
            emailInput.apply {
                    clearFocus()
                    hideKeyboard()
                }
            }

        // events
        actionBtn.setOnClickListener {
            forgetPwdVM.handleForgetPasswordRequest(this, emailInput, emailTLayout)
            forgetPwdVM.errorMessage.observe(this) {
                emailTLayout.error = it
            }
            Intent(this, VerifyCodeForgetPassActivity::class.java).apply {
                putExtra("Email", emailInput.text.toString())
                startActivity(this)
            }
        }

    }
}