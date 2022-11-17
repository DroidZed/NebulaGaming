package tn.esprit.nebulagaming.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import tn.esprit.nebulagaming.R


class RankFragment : Fragment() {

    private lateinit var btneditpage: MaterialButton
    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var phone: EditText
    private lateinit var updatebtn: Button


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btneditpage = view.findViewById(R.id.editpage) as MaterialButton
        username = view.findViewById(R.id.usernameinfo) as EditText
        email = view.findViewById(R.id.useremailinfo) as EditText
        phone = view.findViewById(R.id.userinfophone) as EditText
        updatebtn = view.findViewById(R.id.updatebtn) as Button
        //make btneditpagee if clicked twice hide and unhide updatebtn and  enable and disable edittext
        updatebtn.visibility = View.GONE
        username.isEnabled = false
        email.isEnabled = false
        phone.isEnabled = false
        btneditpage.setOnClickListener {
            if (updatebtn.visibility == View.GONE) {
                updatebtn.visibility = View.VISIBLE
                username.isEnabled = true
                email.isEnabled = true
                phone.isEnabled = true
            } else {
                updatebtn.visibility = View.GONE
                username.isEnabled = false
                email.isEnabled = false
                phone.isEnabled = false
            }
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rank, container, false)
    }
}