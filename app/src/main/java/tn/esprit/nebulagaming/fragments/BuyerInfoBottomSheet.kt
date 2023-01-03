package tn.esprit.nebulagaming.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import tn.esprit.nebulagaming.R

const val EMAIL_PARAM = "EMAIL"
const val PHONE_PARAM = "PHONE"

class BuyerInfoBottomSheet : BottomSheetDialogFragment(R.layout.bottom_sheet_buyer_info) {

    val TAG = "BuyerBottomSheet"

    private lateinit var callBtn: Button
    private lateinit var emailBtn: Button

    private var email: String? = null
    private var phone: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.apply {
            email = getString(EMAIL_PARAM, "")
            phone = getString(PHONE_PARAM, "")
        }
    }

    companion object {

        @JvmStatic
        fun new(email: String, phone: String) =
            BuyerInfoBottomSheet().apply {
                arguments = Bundle().apply {
                    putString(EMAIL_PARAM, email)
                    putString(PHONE_PARAM, phone)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        callBtn = view.findViewById(R.id.callBuyerBtn)
        emailBtn = view.findViewById(R.id.emailBuyerBtn)

        callBtn.setOnClickListener {

            Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phone")
                startActivity(Intent.createChooser(this, "Choose app to dial:"))
            }
        }

        emailBtn.setOnClickListener {
            Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$email")
                putExtra(Intent.EXTRA_SUBJECT, "Contact Buyer")
                putExtra(
                    Intent.EXTRA_EMAIL, arrayListOf(email)
                )
                startActivity(Intent.createChooser(this, "Choose an Email client:"))
            }
        }
    }

}