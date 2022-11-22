package tn.esprit.nebulagaming.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MarketPlaceVP2Adapter(activity: AppCompatActivity, private val itemsCount: Int) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = itemsCount


    override fun createFragment(position: Int): Fragment {
        TODO("Not yet implemented")
    }
}