package tn.esprit.nebulagaming

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.nebulagaming.viewmodels.ForgetPasswordViewModel

@AndroidEntryPoint
class ForgetPasswordActivity : AppCompatActivity() {

    private val forgetPwdVM: ForgetPasswordViewModel by viewModels()

    // private lateinit var emailInput: EditText

    // private lateinit var emailTLayout: TextInputLayout

    // private lateinit var actionBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        // findViewById
/*
        // events
        actionBtn.setOnClickListener {
            forgetPwdVM.handleForgetPasswordRequest(this, emailInput, emailTLayout)

            forgetPwdVM.loading.observe(this) { loadingValue ->

                if (!loadingValue) {

                    forgetPwdVM.errorMessage.observe(this) { error ->
                        if (error != null) {
                            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                        } else {
                            // startActivity(Intent(this, HomeScreen::class.java))
                            Toast.makeText(this, forgetPwdVM.apiMessage.value, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
         */
    }
}