package tn.esprit.nebulagaming

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.nebulagaming.Adapters.ComentAdapter
import tn.esprit.nebulagaming.Adapters.FroumAdapter
import tn.esprit.nebulagaming.models.ComentPost
import tn.esprit.nebulagaming.models.ForumPost

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Commentfragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Commentfragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var adapter: ComentAdapter
    private lateinit var arraylist: ArrayList<ComentPost>
    private lateinit var recyclerView: RecyclerView
    lateinit var idComent: Array<Int>
    lateinit var photoUser: Array<Int>
    lateinit var dateComment: Array<String>
    lateinit var commentsPost: Array<String>
    lateinit var usernameComment: Array<String>
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
        return inflater.inflate(R.layout.fragment_commentfragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialize()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recComment)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = ComentAdapter(arraylist)
        recyclerView.adapter = adapter

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Commentfragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Commentfragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private fun dataInitialize() {
        arraylist = arrayListOf<ComentPost>()
        idComent = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        photoUser = arrayOf(R.drawable.avatar_profile_png_picture,R.drawable.avatar_profile_png_picture,R.drawable.avatar_profile_png_picture,R.drawable.avatar_profile_png_picture,R.drawable.avatar_profile_png_picture,R.drawable.avatar_profile_png_picture,R.drawable.avatar_profile_png_picture,R.drawable.avatar_profile_png_picture,R.drawable.avatar_profile_png_picture,)
        dateComment = arrayOf("2020-12-12", "2020-12-12", "2020-12-12", "2020-12-12", "2020-12-12", "2020-12-12", "2020-12-12", "2020-12-12", "2020-12-12", "2020-12-12")
        commentsPost = arrayOf("comment1", "comment2", "comment3", "comment4", "comment5", "comment6", "comment7", "comment8", "comment9", "comment10")
        usernameComment = arrayOf("user1", "user2", "user3", "user4", "user5", "user6", "user7", "user8", "user9", "user10")
        for (i in photoUser.indices) {
            arraylist.add(ComentPost(idComent[i], usernameComment[i], commentsPost[i], photoUser[i], dateComment[i]))
        }
    }
}