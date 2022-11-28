package tn.esprit.nebulagaming

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.nebulagaming.viewmodels.HomeViewModel

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val homeVM: HomeViewModel by viewModels()

    private lateinit var appToolBar: Toolbar

    private lateinit var drawerLayout: DrawerLayout

    private lateinit var navController: NavController

    private lateinit var navView: NavigationView

    private lateinit var navHostFragment: NavHostFragment

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        appToolBar = findViewById(R.id.mainToolBar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

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

            R.id.publishJobFragment -> {
                val dialog = BottomSheetDialog(this)

                // on below line we are inflating a layout file which we have created.
                val view = layoutInflater.inflate(R.layout.activity_new_job_offer, null)
                // which we are using to dismiss our dialog.

                // on below line we are adding on click listener
                // for our dismissing the dialog button.
                val btnClose = view.findViewById<Button>(R.id.Closepost)

                // on below line we are adding on click listener
                // for our dismissing the dialog button.
                btnClose.setOnClickListener {
                    // on below line we are calling a dismiss
                    // method to close our dialog.
                    dialog.dismiss()
                }
                // below line is use
                // below line is use to set cancelable to avoid
                // closing of dialog box when clicking on the screen.
                dialog.setCancelable(false)

                // on below line we are setting
                // content view to our view.
                dialog.setContentView(view)

                // on below line we are calling
                // a show method to display a dialog.
                dialog.show()
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
}