package tn.esprit.nebulagaming

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.nebulagaming.adapters.ProfileVP2Adapter
import tn.esprit.nebulagaming.fragments.BookmarksFragment
import tn.esprit.nebulagaming.fragments.PostsFragment
import tn.esprit.nebulagaming.fragments.RankFragment
import tn.esprit.nebulagaming.fragments.WishlistFragment

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {

    private lateinit var pViewPager: ViewPager2
    private lateinit var pTabLayout: TabLayout



    private val fragments: MutableList<Fragment> = mutableListOf(
        RankFragment(),
        WishlistFragment(),
        BookmarksFragment(),
        PostsFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        pViewPager = findViewById(R.id.pViewPager)
        pTabLayout = findViewById(R.id.pTabLayout)

        fragments[0] = RankFragment()

        pViewPager.adapter = ProfileVP2Adapter(this, fragments)

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
                        R.drawable.ic_baseline_bookmarks_24,
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



