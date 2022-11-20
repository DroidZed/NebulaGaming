package tn.esprit.nebulagaming

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.nebulagaming.viewmodels.ArticleViewModel

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val homeVM: ArticleViewModel by viewModels()

    private lateinit var appToolBar: Toolbar

    private lateinit var drawerLayout: DrawerLayout

    private lateinit var navController: NavController

    private lateinit var navView: NavigationView

    private lateinit var navHostFragment: NavHostFragment

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    // private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        appToolBar = findViewById(R.id.mainToolBar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        // bottomNav = findViewById(R.id.bottomNavMain)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        setSupportActionBar(appToolBar)

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        setupActionBarWithNavController(navController, appBarConfiguration)

        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)

        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setupWithNavController(navController)

        // setupBottomNavMenu(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_bar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {

            R.id.profile -> {
                startActivity(Intent(this, ProfileActivity::class.java))
                true
            }

            R.id.logout -> {
                homeVM.handleLogOut()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                true
            }

            R.id.notificationFragment -> {
                navController.navigate(R.id.notificationFragment)
                true
            }

            else -> item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
                    || super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    /*
        private fun setupBottomNavMenu(navController: NavController) {
            bottomNav.setupWithNavController(navController)
        }
    */
}