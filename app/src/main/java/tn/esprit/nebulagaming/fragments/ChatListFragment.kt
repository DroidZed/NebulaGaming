package tn.esprit.nebulagaming.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.adapters.ChatListAdapter
import tn.esprit.nebulagaming.data.Conversation


class ChatListFragment : Fragment() {

    private lateinit var chatRV: RecyclerView
    private lateinit var chatAdapter: ChatListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_chat_list, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatRV = view.findViewById(R.id.chatListRV)

        chatAdapter = ChatListAdapter(
            mutableListOf(
                Conversation(
                    "",
                    "Hammdi",
                    "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60",
                    "1m",
                    false,
                    "Hello from the other side!",
                ),
                Conversation(
                    "",
                    "Ahmed",
                    "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60",
                    "1h",
                    true,
                    "How are you?",
                ),
                Conversation(
                    "",
                    "Ali",
                    "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60",
                    "1s",
                    false,
                    "Aw jebtlk sel3a",
                )
            )
        )

        chatRV.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = chatAdapter
        }
    }
}