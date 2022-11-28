package tn.esprit.nebulagaming.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import tn.esprit.nebulagaming.fragments.BookmarksFragment
import tn.esprit.nebulagaming.fragments.PostsFragment
import tn.esprit.nebulagaming.fragments.RankFragment
import tn.esprit.nebulagaming.fragments.WishlistFragment

class ProfileVP2Adapter(activity: AppCompatActivity) :
    FragmentStateAdapter(activity) {

    private val fragments: List<Fragment> = listOf(
        RankFragment(),
        WishlistFragment(),
        BookmarksFragment(),
        PostsFragment()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}