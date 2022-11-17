package tn.esprit.nebulagaming.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.adapters.ComentAdapter
import tn.esprit.nebulagaming.models.ComentPost


class CommentFragment : Fragment() {

    private lateinit var adapter: ComentAdapter
    private lateinit var arraylist: ArrayList<ComentPost>
    private lateinit var recyclerView: RecyclerView
    lateinit var idComent: Array<Int>
    lateinit var photoUser: Array<Int>
    lateinit var dateComment: Array<String>
    lateinit var commentsPost: Array<String>
    lateinit var usernameComment: Array<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comment, container, false)
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

    private fun dataInitialize() {
        arraylist = arrayListOf()
        idComent = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        photoUser = arrayOf(
            R.drawable.avatar_profile_png_picture,
            R.drawable.avatar_profile_png_picture,
            R.drawable.avatar_profile_png_picture,
            R.drawable.avatar_profile_png_picture,
            R.drawable.avatar_profile_png_picture,
            R.drawable.avatar_profile_png_picture,
            R.drawable.avatar_profile_png_picture,
            R.drawable.avatar_profile_png_picture,
            R.drawable.avatar_profile_png_picture,
        )
        dateComment = arrayOf(
            "2020-12-12",
            "2020-12-12",
            "2020-12-12",
            "2020-12-12",
            "2020-12-12",
            "2020-12-12",
            "2020-12-12",
            "2020-12-12",
            "2020-12-12",
            "2020-12-12"
        )
        commentsPost = arrayOf(
            "comment1",
            "comment2",
            "comment3",
            "comment4",
            "comment5",
            "comment6",
            "comment7",
            "comment8",
            "comment9",
            "comment10"
        )
        usernameComment = arrayOf(
            "user1",
            "user2",
            "user3",
            "user4",
            "user5",
            "user6",
            "user7",
            "user8",
            "user9",
            "user10"
        )
        for (i in photoUser.indices) {
            arraylist.add(
                ComentPost(
                    idComent[i],
                    usernameComment[i],
                    commentsPost[i],
                    photoUser[i],
                    dateComment[i]
                )
            )
        }
    }
}