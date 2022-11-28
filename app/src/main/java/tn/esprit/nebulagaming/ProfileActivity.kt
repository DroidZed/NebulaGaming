package tn.esprit.nebulagaming

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import tn.esprit.nebulagaming.adapters.ProfileVP2Adapter

class ProfileActivity : AppCompatActivity() {

    private lateinit var pViewPager: ViewPager2
    private lateinit var pTabLayout: TabLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        pViewPager = findViewById(R.id.pViewPager)
        pTabLayout = findViewById(R.id.pTabLayout)

        pViewPager.adapter = ProfileVP2Adapter(this)

        TabLayoutMediator(pTabLayout, pViewPager) { tab, position ->

            when (position) {
                0 -> tab.apply {
                    text = resources.getString(R.string.profile)
                    icon = ResourcesCompat.getDrawable(resources, R.drawable.name, null)
                }
                1 -> tab.apply {
                    text = resources.getString(R.string.wishlist)
                    icon = ResourcesCompat.getDrawable(resources, R.drawable.like, null)
                }
                2 -> tab.apply {
                    text = resources.getString(R.string.bookmarks)
                    icon = ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.ic_baseline_bookmark_24,
                        null
                    )
                }
                3 -> tab.apply {
                    text = resources.getString(R.string.posts)
                    icon = ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.ic_baseline_forum_24,
                        null
                    )
                }
            }
        }.attach()
    }
}



