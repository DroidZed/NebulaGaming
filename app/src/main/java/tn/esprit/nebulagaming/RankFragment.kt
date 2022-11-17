package tn.esprit.nebulagaming

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.android.material.button.MaterialButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RankFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
private lateinit var btneditpage:MaterialButton
private lateinit var username: EditText
private lateinit var email: EditText
private lateinit var phone: EditText
private lateinit var updatebtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btneditpage= view?.findViewById(R.id.editpage) as MaterialButton
        username= view?.findViewById(R.id.usernameinfo) as EditText
        email= view?.findViewById(R.id.useremailinfo) as EditText
        phone= view?.findViewById(R.id.userinfophone) as EditText
        updatebtn= view?.findViewById(R.id.updatebtn) as Button
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}