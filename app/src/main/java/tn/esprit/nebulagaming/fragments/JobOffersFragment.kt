package tn.esprit.nebulagaming.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.apimodule.models.Article
import tn.esprit.apimodule.models.OffreJob
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.adapters.JobOffersAdapter
import tn.esprit.nebulagaming.data.JobOffer
import tn.esprit.nebulagaming.utils.HelperFunctions
import tn.esprit.nebulagaming.utils.Status
import tn.esprit.nebulagaming.viewmodels.OffreJobViewModel


@AndroidEntryPoint
class JobOffersFragment : Fragment(R.layout.fragment_job_offers) {

    private val JobOfVm:OffreJobViewModel by viewModels()

    private lateinit var jobsRV: RecyclerView
    private lateinit var offrejobs: MutableList<OffreJob>
    private lateinit var swipeContainer: SwipeRefreshLayout


    private lateinit var jobsAdapter: JobOffersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        jobsRV = view.findViewById(R.id.jobsRV)
        offrejobs= mutableListOf()
        swipeContainer = view.findViewById(R.id.swipeContaineroffrejob)

        jobsAdapter = JobOffersAdapter(offrejobs)
        loadData()
        jobsRV.apply {

            adapter = jobsAdapter

            layoutManager = LinearLayoutManager(view.context)
        }

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
                        rs.data?.let {
                            swipeContainer.isRefreshing = false
                            jobsAdapter.addAll(offrejobs)
                            jobsRV.smoothScrollToPosition(0)
                        }
                    }
                    Status.LOADING -> {
                        swipeContainer.isRefreshing = true
                    }
                    Status.ERROR -> {
                        swipeContainer.isRefreshing = false
                        HelperFunctions.toastMsg(view?.context!!, "Could not fetch the api !")
                    }
                }
            }
        }
    }
}