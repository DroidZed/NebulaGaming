package tn.esprit.nebulagaming.fragments

import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.adapters.EventsAdapter
import tn.esprit.nebulagaming.data.EventData
import tn.esprit.nebulagaming.viewmodels.EventViewModel

@AndroidEntryPoint
class EventsFragment : Fragment(R.layout.fragment_events) {

    private lateinit var eventsCal: CalendarView

    private lateinit var eventsRV: RecyclerView

    private lateinit var eventsAdapter: EventsAdapter

    private val eventVM: EventViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventsCal = view.findViewById(R.id.eventsCal)

        eventsRV = view.findViewById(R.id.eventsRV)

        val data = mutableListOf(
            EventData(
                "22/11/2022",
                "23/11/2022",
                "Topic 1",
                "Description 1",
                "The best event ever !"
            ),
            EventData(
                "01/10/2022",
                "02/10/2022",
                "Topic 2",
                "Description 2",
                "The best event ever !"
            ),
            EventData(
                "15/12/2022",
                "31/12/2022",
                "Topic 3",
                "Description 3",
                "The best event ever !"
            ),
        )

        eventsAdapter = EventsAdapter(data)

        eventsRV.apply {
            adapter = eventsAdapter
            layoutManager = LinearLayoutManager(view.context)
        }

        var currentDate = eventsCal.date

        eventsCal.setOnDateChangeListener { _, _, month, dayOfMonth ->

            if (eventsCal.date == currentDate) {
                currentDate = eventsCal.date

                val eventSheet =
                    EventSheet.newInstance(
                        title = "EVENT TITLE",
                        desc = resources.getString(R.string.lorem),
                        period = "${dayOfMonth}/${month + 1} @ 9PM - ${dayOfMonth}/${month + 1} @ 10PM",
                        topic = "20% EXP BOOST !!",
                        image = "https://i.pinimg.com/originals/1b/f6/fa/1bf6fa809078cd185a3c9e54eb127ac8.jpg"
                    )
                eventSheet.show(parentFragmentManager, EventSheet.TAG)
            }
        }
    }
}