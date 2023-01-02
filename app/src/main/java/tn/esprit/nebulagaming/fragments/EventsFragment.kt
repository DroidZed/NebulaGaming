package tn.esprit.nebulagaming.fragments


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.adapters.EventsAdapter
import tn.esprit.nebulagaming.utils.HelperFunctions.toastMsg
import tn.esprit.nebulagaming.utils.Status
import tn.esprit.nebulagaming.viewmodels.EventViewModel
import java.util.*


@AndroidEntryPoint
class EventsFragment : Fragment(R.layout.fragment_events) {

    private lateinit var eventsCal: CalendarView

    private lateinit var eventsRV: RecyclerView

    private lateinit var eventsAdapter: EventsAdapter

    private lateinit var layoutMan: LinearLayoutManager

    private lateinit var swipeEventsLayout: SwipeRefreshLayout

    private val eventVM: EventViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventsCal = view.findViewById(R.id.eventsCal)
        eventsRV = view.findViewById(R.id.eventsRV)
        swipeEventsLayout = view.findViewById(R.id.swipeEventsLayout)

        eventsAdapter = EventsAdapter(mutableListOf())

        layoutMan = LinearLayoutManager(view.context)

        loadMonthlyEvents(
            view,
            eventsCal.currentPageDate[Calendar.MONTH] + 1,
            eventsCal.currentPageDate[Calendar.YEAR]
        )

        swipeEventsLayout.setOnRefreshListener {
            loadMonthlyEvents(
                view,
                eventsCal.currentPageDate[Calendar.MONTH] + 1,
                eventsCal.currentPageDate[Calendar.YEAR],
                false
            )
        }

        eventsRV.apply {
            adapter = eventsAdapter
            layoutManager = layoutMan
        }

        eventsCal.setOnPreviousPageChangeListener(object : OnCalendarPageChangeListener {
            override fun onChange() {
                loadMonthlyEvents(
                    view,
                    eventsCal.currentPageDate[Calendar.MONTH] + 1,
                    eventsCal.currentPageDate[Calendar.YEAR]
                )
            }
        })

        eventsCal.setOnForwardPageChangeListener(object : OnCalendarPageChangeListener {
            override fun onChange() {
                loadMonthlyEvents(
                    view,
                    eventsCal.currentPageDate[Calendar.MONTH] + 1,
                    eventsCal.currentPageDate[Calendar.YEAR]
                )
            }
        })
    }

    // private fun setupEOTD(daily: Event) {}

    private fun loadMonthlyEvents(view: View, month: Int?, year: Int?, initialRun: Boolean = true) =
        runBlocking {

            eventsAdapter.clear()

            eventVM.fetchEventsOfTheMonthByYear(view.context, month, year)
                .observe(viewLifecycleOwner) {

                    it?.let { rs ->
                        when (rs.status) {
                            Status.LOADING -> if (initialRun) swipeEventsLayout.isRefreshing = true

                            Status.ERROR -> {

                                if (initialRun)
                                    swipeEventsLayout.isRefreshing = false
                                toastMsg(
                                    view.context,
                                    "Couldn't load events, please try again later."
                                )
                            }

                            Status.SUCCESS -> {
                                swipeEventsLayout.isRefreshing = false

                                eventsAdapter.addAll(rs.data!!.toMutableList())
                            }
                        }
                    }
                }
        }
}