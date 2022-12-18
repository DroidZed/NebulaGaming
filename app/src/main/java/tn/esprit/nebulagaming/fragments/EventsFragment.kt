package tn.esprit.nebulagaming.fragments


import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import tn.esprit.apimodule.models.Event
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.adapters.EventsAdapter
import tn.esprit.nebulagaming.utils.HelperFunctions.toastMsg
import tn.esprit.nebulagaming.utils.Status
import tn.esprit.nebulagaming.viewmodels.EventViewModel
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.stream.Collectors
import java.util.stream.LongStream


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
                eventsCal.currentPageDate[Calendar.YEAR]
            )
            eventsRV.refreshDrawableState()
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

    private fun setupCalendar(eventList: List<Event>) {

        val eventDates = mutableListOf<EventDay>()

        eventList.forEach { event ->

            val startDate = eventVM.parseDate(event.startDateTime)
            val endDate = eventVM.parseDate(event.endDateTime)

            val numOfDaysBetween: Long =
                ChronoUnit.DAYS.between(startDate, endDate)

            val allDatesInBetween: MutableList<LocalDateTime> =
                LongStream.iterate(0) { i -> i + 1 }
                    .limit(numOfDaysBetween)
                    .mapToObj { i -> startDate!!.plusDays(i) }
                    .collect(Collectors.toList())

            allDatesInBetween.forEach { d ->
                val calendar = Calendar.getInstance()
                calendar.apply {
                    set(Calendar.YEAR, d.year)
                    set(Calendar.MONTH, d.monthValue)
                    set(Calendar.DAY_OF_MONTH, d.dayOfMonth)
                    set(Calendar.HOUR, d.hour)
                    set(Calendar.MINUTE, d.minute)
                    set(Calendar.SECOND, d.second)
                }
                eventsCal.setDate(calendar)
                eventDates.add(
                    EventDay(
                        calendar,
                        R.drawable.dot_filled,
                        Color.parseColor(if (event.bonus <= 30) "#5a228b" else "#8b225a")
                    )
                )
            }
        }
        eventsCal.setEvents(eventDates)
    }


    // private fun setupEOTD(daily: Event) {}

    private fun loadMonthlyEvents(view: View, month: Int?, year: Int?) = runBlocking {

        eventsAdapter.clear()

        eventVM.fetchEventsOfTheMonthByYear(view.context, month, year).observe(viewLifecycleOwner) {

            it?.let { rs ->
                when (rs.status) {
                    Status.LOADING -> {
                        swipeEventsLayout.isRefreshing = true
                    }
                    Status.ERROR -> {

                        swipeEventsLayout.isRefreshing = false
                        toastMsg(
                            view.context,
                            "Couldn't load events, please try again later."
                        )
                    }

                    Status.SUCCESS ->
                        rs.data?.apply {
                            swipeEventsLayout.isRefreshing = false
                            eventsAdapter.addAll(this.toMutableList())
                            setupCalendar(this)
                        }
                }
            }
        }
    }
}