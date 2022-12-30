package tn.esprit.nebulagaming.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.adapters.JobOffersAdapter
import tn.esprit.nebulagaming.utils.HelperFunctions.toastMsg
import tn.esprit.nebulagaming.utils.Status
import tn.esprit.nebulagaming.viewmodels.OffreJobViewModel


    @AndroidEntryPoint
class JobOffersFragment : Fragment(R.layout.fragment_job_offers) {

    private val JobOfVm: OffreJobViewModel by viewModels()

    private lateinit var jobsRV: RecyclerView
    private lateinit var swipeContainer: SwipeRefreshLayout


    private lateinit var jobsAdapter: JobOffersAdapter

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        jobsRV = view.findViewById(R.id.jobsRV)
        swipeContainer = view.findViewById(R.id.swipeContaineroffrejob)

        jobsAdapter = JobOffersAdapter(mutableListOf())

        jobsRV.apply {

            adapter = jobsAdapter
            layoutManager = LinearLayoutManager(view.context)
        }

        loadData()

        swipeContainer.setOnRefreshListener {
            jobsAdapter.clear()
            loadData()
        }
    }

    private fun loadData() {

        JobOfVm.loadOffreJob(view?.context!!).observe(requireActivity()) {
            it?.let { rs ->
                when (rs.status) {
                    Status.SUCCESS -> {
                        rs.data?.apply {
                            swipeContainer.isRefreshing = false
                            jobsAdapter.addAll(this)
                            jobsRV.smoothScrollToPosition(0)
                        }
                    }
                    Status.LOADING -> {
                        swipeContainer.isRefreshing = true
                    }
                    Status.ERROR -> {
                        swipeContainer.isRefreshing = false
                        toastMsg(view?.context!!, it.message!!)
                        Log.e("OFFER-JOB", it.message)
                    }
                }
            }
        }
    }
}