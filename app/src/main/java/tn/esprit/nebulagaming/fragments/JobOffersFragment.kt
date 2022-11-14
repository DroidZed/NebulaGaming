package tn.esprit.nebulagaming.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.adapters.JobOffersAdapter
import tn.esprit.nebulagaming.data.JobOffer


class JobOffersFragment : Fragment() {

    private lateinit var jobsRV: RecyclerView

    private lateinit var jobsAdapter: JobOffersAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_job_offers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        jobsRV = view.findViewById(R.id.jobsRV)

        jobsAdapter = JobOffersAdapter(
            mutableListOf(
                JobOffer(
                    "Microsoft",
                    "Software Engineer",
                    "microsoft@exmaple.com",
                    "https://microsoft.com",
                    period = "6 months",
                    stDate = "02/01/2023",
                    address = "Silicon Valley"
                )
            )
        )

        jobsRV.apply {

            adapter = jobsAdapter

            layoutManager = LinearLayoutManager(view.context)
        }
    }
}