package tn.esprit.nebulagaming

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.nebulagaming.adapters.MarketPlaceVP2Adapter

@AndroidEntryPoint
class MarketplaceActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var mpTopBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marketplace)

        tabLayout = findViewById(R.id.tabLayout)
        viewPager2 = findViewById(R.id.mpViewPager)
        mpTopBar = findViewById(R.id.topBarMp)

        viewPager2.adapter = MarketPlaceVP2Adapter(this)

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = resources.getString(R.string.marketplace)
                1 -> tab.text = resources.getString(R.string.your_products)
                2 -> tab.text = resources.getString(R.string.wishlist)
            }
        }.attach()

        setSupportActionBar(mpTopBar)

        mpTopBar.setNavigationOnClickListener {
            finish()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}