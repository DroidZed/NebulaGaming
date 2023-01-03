package tn.esprit.nebulagaming.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.utils.HelperFunctions.getUrlFromResource
import tn.esprit.nebulagaming.utils.HelperFunctions.openLink


class AboutFragment : Fragment(R.layout.fragment_about) {

    private lateinit var contactBtn: Button
    private lateinit var dev1ChipLI: Chip
    private lateinit var dev2ChipLI: Chip
    private lateinit var dev1ChipGH: Chip
    private lateinit var dev2ChipGH: Chip
    private lateinit var dev2ChipFB: Chip

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contactBtn = view.findViewById(R.id.contactBtn)
        dev1ChipLI = view.findViewById(R.id.dev1ChipLI)
        dev2ChipLI = view.findViewById(R.id.dev2ChipLI)
        dev1ChipGH = view.findViewById(R.id.dev1ChipGH)
        dev2ChipGH = view.findViewById(R.id.dev2ChipGH)
        dev2ChipFB = view.findViewById(R.id.dev2ChipFB)

        contactBtn.setOnClickListener {
            Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:nebula.gaming2@outlook.com")
                putExtra(Intent.EXTRA_SUBJECT, "Contact Nebula Team")
                putExtra(Intent.EXTRA_EMAIL, getUrlFromResource(R.string.ng_email, resources))
                startActivity(Intent.createChooser(this, "Choose an Email client :"))
            }
        }

        dev1ChipLI.setOnClickListener {
            openLink(requireContext(), getUrlFromResource(R.string.lin_aymen, resources))
        }

        dev2ChipLI.setOnClickListener {
            openLink(requireContext(), getUrlFromResource(R.string.lin_yassine, resources))
        }

        dev1ChipGH.setOnClickListener {
            openLink(requireContext(), getUrlFromResource(R.string.gh_aymen, resources))
        }

        dev2ChipGH.setOnClickListener {
            openLink(requireContext(), getUrlFromResource(R.string.gh_yassine, resources))
        }

        dev2ChipFB.setOnClickListener {
            openLink(requireContext(), getUrlFromResource(R.string.fb_yassine, resources))
        }

    }

}