package tn.esprit.nebulagaming

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView
import com.mikhaellopez.circularimageview.CircularImageView
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.nebulagaming.utils.HelperFunctions.toastMsg
import tn.esprit.nebulagaming.utils.HelperFunctions.usePicasso
import tn.esprit.nebulagaming.utils.Status
import tn.esprit.nebulagaming.viewmodels.HomeViewModel
import tn.esprit.shared.Consts.FUNCTION_URL


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val homeVM: HomeViewModel by viewModels()

    private lateinit var appToolBar: Toolbar

    private lateinit var drawerLayout: DrawerLayout

    private lateinit var navController: NavController

    private lateinit var navView: NavigationView

    private lateinit var headerView: View

    private lateinit var headerImage: CircularImageView

    private lateinit var usernameHeader: TextView

    private lateinit var levelHeader: TextView

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

        headerView = navView.getHeaderView(0)
        headerImage = headerView.findViewById(R.id.headerImage)
        usernameHeader = headerView.findViewById(R.id.usernameHeader)
        levelHeader = headerView.findViewById(R.id.levelHeader)

        homeVM.fetchUserInfo(this).observe(this) {
            it?.let { rs ->
                when (rs.status) {
                    Status.SUCCESS -> {
                        rs.data?.let { u ->
                            usePicasso(
                                "$FUNCTION_URL/img/${u.photo}",
                                R.drawable.logonv,
                                headerImage
                            )
                            usernameHeader.text = u.name
                            levelHeader.apply {
                                textSize = if (u.level > 99) 17f else 25f
                                text = "${u.level}"
                            }

                            val decor = window.decorView as ViewGroup

                            val outViews: ArrayList<View> = ArrayList()

                            decor.findViewsWithText(
                                outViews, getString(R.string.overflow_btn_ctn_desc),
                                View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION
                            )

                            if (outViews.isNotEmpty()) {

                                usePicasso(
                                    "$FUNCTION_URL/img/${u.photo}",
                                    R.drawable.logonv,
                                    outViews[0] as AppCompatImageView
                                )
                            }

                        }
                    }
                    Status.LOADING -> {
                        //
                    }
                    Status.ERROR -> {
                        toastMsg(this, rs.message!!)
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_bar_menu, menu)

        menu?.getItem(0)?.isVisible = homeVM.checkIfCompany()

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
                startActivity(Intent(this, NewJobOfferActivity::class.java))
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