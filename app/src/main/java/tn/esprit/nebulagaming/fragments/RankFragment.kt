package tn.esprit.nebulagaming.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.utils.HelperFunctions.toastMsg
import tn.esprit.nebulagaming.viewmodels.ProfileViewModel
import tn.esprit.roommodule.models.UserProfile

@AndroidEntryPoint
class RankFragment : Fragment(R.layout.fragment_rank) {

    private val viewModel: ProfileViewModel by viewModels()

    private lateinit var editPageBtn: MaterialButton
    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var phone: EditText
    private lateinit var updateBtn: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editPageBtn = view.findViewById(R.id.editPageBtn)
        username = view.findViewById(R.id.usernameinfo)
        email = view.findViewById(R.id.useremailinfo)
        phone = view.findViewById(R.id.userinfophone)
        updateBtn = view.findViewById(R.id.updateBtn)

        val data = viewModel.fetchUserInfoFromDb()

        if (data != null) setupUi(data.user!!)

        val group = listOf(
            updateBtn,
            username,
            email,
            phone
        )

        disableComponents(group)

        updateBtn.setOnClickListener {

            viewModel.handleUpdate(view.context, username.text.toString(), phone.text.toString())

            viewModel.successMessage.observe(viewLifecycleOwner) { msg ->
                if (msg != null) {
                    toastMsg(view.context, msg)
                    disableComponents(group)
                    viewModel.nameUser.postValue(username.text.toString())
                } else
                    toastMsg(view.context, viewModel.errorMessage.value!!)
            }

        }

        editPageBtn.setOnClickListener {
            if (updateBtn.visibility == View.GONE)
                enableComponents(group)
            else
                disableComponents(group)
        }
    }

    private fun setupUi(user: UserProfile) {

        username.setText(user.name, TextView.BufferType.EDITABLE)
        email.setText(user.email, TextView.BufferType.EDITABLE)
        phone.setText(user.phone, TextView.BufferType.EDITABLE)
    }

    private fun disableComponents(group: List<TextView>) {
        group.forEach {
            it.isEnabled = false
        }
        updateBtn.visibility = View.GONE
    }

    private fun enableComponents(group: List<TextView>) {
        group.forEach {
            it.isEnabled = true
        }
        updateBtn.visibility = View.VISIBLE
    }
}