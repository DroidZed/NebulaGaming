package tn.esprit.nebulagaming

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.nebulagaming.Adapters.FroumAdapter
import tn.esprit.nebulagaming.Adapters.WishlistAdapter
import tn.esprit.nebulagaming.models.ForumPost
import tn.esprit.nebulagaming.models.Wishlist

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WishlistFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WishlistFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var adapter: WishlistAdapter
    private lateinit var arraylist: ArrayList<Wishlist>
    private lateinit var recyclerView: RecyclerView
    lateinit var idWish: Array<Int>
    lateinit var photoProduct: Array<Int>
    lateinit var priceProduct: Array<String>
    lateinit var nomProduct: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wishlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialise()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recycler_viewwishlist)
        recyclerView.layoutManager = layoutManager
        adapter = WishlistAdapter(arraylist)
        recyclerView.adapter = adapter
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WishlistFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WishlistFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private fun dataInitialise(){
        arraylist = arrayListOf<Wishlist>()
        idWish = arrayOf(1,2,3,4,5)
        photoProduct = arrayOf(R.drawable.goldreward,R.drawable.goldreward,R.drawable.goldreward,R.drawable.goldreward,R.drawable.goldreward)
        priceProduct = arrayOf("100","100","100","100","100")
        nomProduct = arrayOf("Assassin's Creed","Assassin's Creed","Assassin's Creed","Assassin's Creed","Assassin's Creed")
        for (i in photoProduct.indices){
            arraylist.add(Wishlist(idWish[i],nomProduct[i],photoProduct[i],priceProduct[i]))
        }
    }
}