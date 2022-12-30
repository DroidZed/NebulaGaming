package tn.esprit.nebulagaming.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.nebulagaming.adapters.ListUserAdapter
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.utils.HelperFunctions.toastMsg
import tn.esprit.nebulagaming.viewmodels.ProfileViewModel
import tn.esprit.nebulagaming.utils.Status

@AndroidEntryPoint
class ListusersFragment : Fragment(R.layout.fragment_listusers) {

    private val profVM: ProfileViewModel by viewModels()
    private lateinit var listuRecy: RecyclerView
    private lateinit var listAdpater: ListUserAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listuRecy = view.findViewById(R.id.listuserRecy)
        listAdpater = ListUserAdapter(mutableListOf())
        listuRecy.apply {
            adapter = listAdpater
            layoutManager = LinearLayoutManager(view.context)

        }

        loadData()
    }

    private fun loadData() {
        profVM.loadUser(view?.context!!).observe(requireActivity()) {
            it?.let { rs ->
                when (rs.status) {
                    Status.SUCCESS -> {
                        rs.data?.apply {
                            listAdpater.addAll(this)
                        }
                    }
                    Status.ERROR -> {

                        Log.e("ListusersFragment", rs.message!!)
                    }
                    Status.LOADING -> {
                        Log.d("TAG", "loadData: Loading")
                    }
                }
            }
        }
    }


}