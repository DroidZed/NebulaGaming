package tn.esprit.nebulagaming.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.adapters.CommentAdapter
import tn.esprit.nebulagaming.data.CommentPost


class CommentFragment : Fragment(R.layout.fragment_comment) {

    private lateinit var adapter: CommentAdapter
    private lateinit var arraylist: ArrayList<CommentPost>
    private lateinit var recyclerView: RecyclerView
    lateinit var idComent: Array<Int>
    lateinit var photoUser: Array<Int>
    lateinit var dateComment: Array<String>
    lateinit var commentsPost: Array<String>
    lateinit var usernameComment: Array<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialize()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recComment)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = CommentAdapter(arraylist)
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
                CommentPost(
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