package tn.esprit.nebulagaming

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.nebulagaming.Adapters.FroumAdapter
import tn.esprit.nebulagaming.models.ForumPost

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PostsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
            private lateinit var adapter: FroumAdapter
            private lateinit var arraylist: ArrayList<ForumPost>
            private lateinit var recyclerView: RecyclerView
    lateinit var idPost: Array<Int>
    lateinit var photoUser: Array<Int>
    lateinit var photoPost: Array<Int>
    lateinit var postDate: Array<String>
    lateinit var postText: Array<String>
    lateinit var postLikes: Array<String>
    lateinit var postComments: Array<String>
    lateinit var postSaved: Array<String>
    lateinit var postUser: Array<String>
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
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataInitialize()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recycler_viewposts)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = FroumAdapter(arraylist , requireContext())
        recyclerView.adapter = adapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PostsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PostsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }


    }

    private fun dataInitialize() {
        arraylist = arrayListOf<ForumPost>()
        idPost = arrayOf(1, 2, 3, 4, 5)
        photoUser = arrayOf(
            R.drawable.avatar_profile_png_picture,
            R.drawable.avatar_profile_png_picture,
            R.drawable.avatar_profile_png_picture,
            R.drawable.avatar_profile_png_picture,
            R.drawable.avatar_profile_png_picture,
        )
        photoPost = arrayOf(
            R.drawable.imgepost,
            R.drawable.imgepost,
            R.drawable.imgepost,
            R.drawable.imgepost,
            R.drawable.imgepost,
        )
        postDate = arrayOf(
            "22_05_2021",
            "22_05_2021",
            "22_05_2021",
            "22_05_2021",
            "22_05_2021",
        )
        postText = arrayOf(
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod, nunc ut aliquam tincidunt, nunc nisl aliquam nisl, eget aliquam nisl nisl sit amet nisl. Sed euismod, nunc ut aliquam tincidunt, nunc nisl aliquam nisl, eget aliquam nisl nisl sit amet nisl.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod, nunc ut aliquam tincidunt, nunc nisl aliquam nisl, eget aliquam nisl nisl sit amet nisl. Sed euismod, nunc ut aliquam tincidunt, nunc nisl aliquam nisl, eget aliquam nisl nisl sit amet nisl.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod, nunc ut aliquam tincidunt, nunc nisl aliquam nisl, eget aliquam nisl nisl sit amet nisl. Sed euismod, nunc ut aliquam tincidunt, nunc nisl aliquam nisl, eget aliquam nisl nisl sit amet nisl.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod, nunc ut aliquam tincidunt, nunc nisl aliquam nisl, eget aliquam nisl nisl sit amet nisl. Sed euismod, nunc ut aliquam tincidunt, nunc nisl aliquam nisl, eget aliquam nisl nisl sit amet nisl.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod, nunc ut aliquam tincidunt, nunc nisl aliquam nisl, eget aliquam nisl nisl sit amet nisl. Sed euismod, nunc ut aliquam tincidunt, nunc nisl aliquam nisl, eget aliquam nisl nisl sit amet nisl.",
        )
        postLikes = arrayOf(
            "100",
            "100",
            "100",
            "100",
            "100",
        )
        postComments = arrayOf(
            "100",
            "100",
            "100",
            "100",
            "100",
        )
        postSaved = arrayOf(
            "100",
            "100",
            "100",
            "100",
            "100",
        )

        postUser = arrayOf(
            "User 1",
            "User 2",
            "User 3",
            "User 4",
            "User 5",
        )
        for (i in photoUser.indices) {
            arraylist.add(
                ForumPost(
                    idPost[i],
                    postUser[i],
                    photoUser[i],
                    photoPost[i],
                    postDate[i],
                    postText[i],
                    postLikes[i],
                    postSaved[i],
                )
            )
        }
    }
}