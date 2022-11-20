package tn.esprit.nebulagaming

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.nebulagaming.viewmodels.MainViewModel


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainVM: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_NebulaGaming)
        setContentView(R.layout.activity_main)

        when {
            !mainVM.isUserLoggedIn() -> startActivity(Intent(this, LoginActivity::class.java))

            else -> startActivity(Intent(this, HomeActivity::class.java))
        }
        finish()
    }

}