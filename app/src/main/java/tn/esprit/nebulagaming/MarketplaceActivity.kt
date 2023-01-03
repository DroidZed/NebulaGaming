package tn.esprit.nebulagaming

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.nebulagaming.adapters.MarketPlaceVP2Adapter
import tn.esprit.nebulagaming.fragments.MarketplaceFragment
import tn.esprit.nebulagaming.fragments.MyShopFragment
import tn.esprit.nebulagaming.fragments.WishlistFragment

@AndroidEntryPoint
class MarketplaceActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var mpTopBar: Toolbar
    private lateinit var mkAdapter: MarketPlaceVP2Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marketplace)

        tabLayout = findViewById(R.id.tabLayout)
        viewPager2 = findViewById(R.id.mpViewPager)
        mpTopBar = findViewById(R.id.topBarMp)

        val fragments: MutableList<Fragment> = mutableListOf(
            MarketplaceFragment(),
            MyShopFragment(),
            WishlistFragment()
        )

        mkAdapter = MarketPlaceVP2Adapter(
            supportFragmentManager,
            fragments,
            lifecycle
        )

        viewPager2.adapter = mkAdapter

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

        viewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            var currPos = 0

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currPos = position
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                if ((state == ViewPager2.SCROLL_STATE_IDLE) and (currPos == 2)) {
                    refreshFragment(2, WishlistFragment())
                }
            }
        })
    }

    private fun refreshFragment(index: Int, fragment: Fragment) {
        Handler(Looper.getMainLooper()).postDelayed({
            mkAdapter.refreshFragment(index, fragment)
        }, 5000)
    }
}