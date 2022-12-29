@file:Suppress("DEPRECATION")

package tn.esprit.nebulagaming

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import net.openid.appauth.*
import tn.esprit.nebulagaming.utils.clearText
import tn.esprit.nebulagaming.utils.hideKeyboard
import tn.esprit.nebulagaming.utils.on
import tn.esprit.nebulagaming.viewmodels.LoginViewModel


@Suppress("DEPRECATION")
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val loginVM: LoginViewModel by viewModels()
    private lateinit var DiscordButton: MaterialButton
    private lateinit var emailET: EditText
    private lateinit var passwordET: EditText

    private lateinit var emailTL: TextInputLayout
    private lateinit var passwordTL: TextInputLayout

    private lateinit var loginBtn: Button

    private lateinit var forgotPasswordLink: TextView
    private lateinit var signUpPrompt: TextView

    private lateinit var googleBtn: MaterialButton

    var gso: GoogleSignInOptions? = null
    var gsc: GoogleSignInClient? = null

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
                    listOf(emailET, passwordET).forEach {
                        it.clearText()
                    }
                    Toast.makeText(this, "Logged in !", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
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

        DiscordButton = findViewById(R.id.DiscordButton)
        DiscordButton.setOnClickListener {
            val serviceConfig = AuthorizationServiceConfiguration(
                Uri.parse("https://discord.com/api/oauth2/authorize"),  // authorization endpoint
                Uri.parse("")  // token endpoint
            )

            val clientId = "1051808463195492422"

            val authRequestBuilder = AuthorizationRequest.Builder(
                serviceConfig,  // the authorization service configuration
                clientId,  // the client ID, typically pre-registered and static
                ResponseTypeValues.CODE,  // the response_type value: we want a code
                Uri.parse("droidchef://oauth/discord")// the redirect URI to which the auth response is sent
            )

            val discordScopes = listOf("identity", "email")

            val authRequest = authRequestBuilder
                .setScopes(discordScopes)
                .setCodeVerifier(null, null, null) // THIS IS A HACK, NOT RECOMMENDED, BUT NEEDED
                .build()

            val authService = AuthorizationService(this as LoginActivity)

            val authIntent = authService.getAuthorizationRequestIntent(authRequest)
            startActivityForResult(authIntent, 200)

        }



        gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        gsc = GoogleSignIn.getClient(this, gso!!)

        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            HomeActivity()
        }

        googleBtn = findViewById(R.id.googleBtn)
        googleBtn.setOnClickListener {
            gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
            gsc = GoogleSignIn.getClient(this, gso!!)
            val signInIntent = gsc!!.signInIntent
            startActivityForResult(signInIntent, 100)
        }
    }

    private fun HomeActivity() {

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                task.getResult(ApiException::class.java)
                HomeActivity()
            } catch (e: ApiException) {
                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}