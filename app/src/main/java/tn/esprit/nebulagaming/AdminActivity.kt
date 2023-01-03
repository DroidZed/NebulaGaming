package tn.esprit.nebulagaming

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.nebulagaming.fragments.ListusersFragment
@AndroidEntryPoint
class AdminActivity : AppCompatActivity() {
    private lateinit var fragcontausers: FragmentContainerView
    private lateinit var fragusers:ListusersFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        fragusers = ListusersFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragcontausers,fragusers).commit()
    }
}
