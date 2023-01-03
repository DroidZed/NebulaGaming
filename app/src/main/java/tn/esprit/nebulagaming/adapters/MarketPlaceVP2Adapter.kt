package tn.esprit.nebulagaming.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class MarketPlaceVP2Adapter(
    fragManager: FragmentManager,
    var fragments: MutableList<Fragment>,
    lifecycle: Lifecycle
) :
    FragmentStateAdapter(fragManager, lifecycle) {


    fun add(index: Int, fragment: Fragment) {
        fragments.add(index, fragment)
        notifyItemInserted(index)
    }

    fun refreshFragment(index: Int, fragment: Fragment) {
        fragments[index] = fragment
        notifyItemChanged(index)
    }

    fun remove(index: Int) {
        fragments.removeAt(index)
        notifyItemRemoved(index)
    }

    override fun getItemId(position: Int): Long {
        return fragments[position].hashCode().toLong()
    }

    override fun containsItem(itemId: Long): Boolean {
        return fragments.find { it.hashCode().toLong() == itemId } != null
    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}