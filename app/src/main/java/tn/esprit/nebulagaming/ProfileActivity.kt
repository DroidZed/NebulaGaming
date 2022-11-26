package tn.esprit.nebulagaming

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView
    private lateinit var navController: NavController

    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        bottomNav = findViewById(R.id.profileBottomNav)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.p_nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupBottomNavMenu(navController)

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.rankFragment -> {
                    navController.navigate(R.id.rankFragment)
                    true
                }
                R.id.bookmarksFragment -> {
                    navController.apply {
                        navigate(R.id.bookmarksFragment)

                    }
                    true
                }
                R.id.wishlistFragment -> {
                    navController.apply {
                        navigate(R.id.wishlistFragment)

                    }
                    true
                }
                R.id.postsFragment -> {
                    navController.apply {
                        navigate(R.id.postsFragment)

                    }
                    true
                }
                else -> false
            }
        }
    }

    private fun setupBottomNavMenu(navController: NavController) {
        bottomNav.setupWithNavController(navController)
    }

}



