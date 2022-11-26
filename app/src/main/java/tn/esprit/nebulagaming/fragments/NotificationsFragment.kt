package tn.esprit.nebulagaming.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.adapters.NotificationsAdapter
import tn.esprit.nebulagaming.data.Notification


class NotificationsFragment : Fragment(R.layout.fragment_notifications) {

    private lateinit var notifsRV: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notifsRV = view.findViewById(R.id.notifsRV)

        notifsRV.apply {

            adapter = NotificationsAdapter(
                mutableListOf(
                    Notification(
                        title = "Title 1",
                        body = "this is the 1st notification",
                        image = "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
                    ),
                    Notification(
                        title = "Title 2",
                        body = "this is the 2nd notification",
                        image = "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
                    ),
                    Notification(
                        title = "Title 3",
                        body = "this is the 3th notification",
                        image = "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
                    ),
                    Notification(
                        title = "Title 4",
                        body = "this is the 4th notification",
                        image = "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
                    ),
                    Notification(
                        title = "Title 5",
                        body = "this is the 5th notification",
                        image = "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
                    ),
                    Notification(
                        title = "Title 6",
                        body = "this is the 6th notification",
                        image = "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
                    ),
                    Notification(
                        title = "Title 7",
                        body = "this is the 7th notification",
                        image = "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
                    ),
                    Notification(
                        title = "Title 8",
                        body = "this is the 8th notification",
                        image = "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
                    ),
                    Notification(
                        title = "Title 9",
                        body = "this is the 9th notification",
                        image = "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
                    ),
                )
            )

            layoutManager = LinearLayoutManager(view.context)
        }
    }
}