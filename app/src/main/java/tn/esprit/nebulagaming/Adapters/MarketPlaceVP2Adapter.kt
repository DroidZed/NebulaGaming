package tn.esprit.nebulagaming.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import tn.esprit.nebulagaming.fragments.MarketplaceFragment
import tn.esprit.nebulagaming.fragments.MyShopFragment
import tn.esprit.nebulagaming.fragments.WishlistFragment

class MarketPlaceVP2Adapter(activity: AppCompatActivity) :
    FragmentStateAdapter(activity) {

    private var fragments: List<Fragment> = listOf(
        MarketplaceFragment(),
        MyShopFragment(),
        WishlistFragment()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}