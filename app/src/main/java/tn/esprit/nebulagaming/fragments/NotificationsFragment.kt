package tn.esprit.nebulagaming.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.adapters.NotificationsAdapter
import tn.esprit.nebulagaming.viewmodels.NotificationsViewModel

@AndroidEntryPoint
class NotificationsFragment : Fragment(R.layout.fragment_notifications) {

    private val notifVm: NotificationsViewModel by viewModels()

    private lateinit var notifsRV: RecyclerView
    private lateinit var notificationsAdapter: NotificationsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notifsRV = view.findViewById(R.id.notifsRV)

        notificationsAdapter = NotificationsAdapter(mutableListOf())

        linearLayoutManager = LinearLayoutManager(view.context)

        notifsRV.apply {
            adapter = notificationsAdapter
            layoutManager = linearLayoutManager
        }

        notificationsAdapter.addAll(notifVm.getUserNotifications()!!.toMutableList())
    }
}