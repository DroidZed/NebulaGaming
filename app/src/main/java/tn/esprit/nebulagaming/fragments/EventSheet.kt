package tn.esprit.nebulagaming.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso
import tn.esprit.nebulagaming.R


private const val ARG_TITLE = "ARG_TITLE"
private const val ARG_TOPIC = "ARG_TOPIC"
private const val ARG_DESC = "ARG_DESC"
private const val ARG_PERIOD = "ARG_PERIOD"
private const val ARG_IMG = "ARG_IMG"

class EventSheet() : BottomSheetDialogFragment() {

    private lateinit var sheetBgEv: ImageView
    private lateinit var eventTitleSh: TextView
    private lateinit var eventTopicSh: TextView
    private lateinit var eventPeriodSh: TextView
    private lateinit var eventDescSh: TextView
    private lateinit var eventImageSh: ImageView
    private lateinit var closeBtnSh: Button

    private var titleArg: String? = null
    private var topicArg: String? = null
    private var descArg: String? = null
    private var periodArg: String? = null
    private var imgArg: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            titleArg = it.getString(ARG_TITLE)
            topicArg = it.getString(ARG_TOPIC)
            descArg = it.getString(ARG_DESC)
            periodArg = it.getString(ARG_PERIOD)
            imgArg = it.getString(ARG_IMG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.event_sheet, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sheetBgEv = view.findViewById(R.id.sheetBgEv)

        eventTitleSh = view.findViewById(R.id.eventTitleSh)
        eventTopicSh = view.findViewById(R.id.eventTopicSh)
        eventPeriodSh = view.findViewById(R.id.eventPeriodSh)
        eventDescSh = view.findViewById(R.id.eventDescSh)

        eventImageSh = view.findViewById(R.id.eventImageSh)

        closeBtnSh = view.findViewById(R.id.closeBtnSh)


        Picasso.get()
            .load(imgArg)
            .placeholder(R.drawable.event_wallpaper)
            .into(eventImageSh)

        Picasso.get()
            .load(imgArg)
            .placeholder(R.drawable.event_wallpaper)
            .into(sheetBgEv)

        eventTitleSh.text = titleArg
        eventTopicSh.text = topicArg
        eventPeriodSh.text = periodArg
        eventDescSh.text = descArg

        closeBtnSh.setOnClickListener {
            dismiss()
        }
    }

    companion object {

        const val TAG = "EventSheet"

        @JvmStatic
        fun newInstance(
            title: String,
            topic: String,
            desc: String,
            period: String,
            image: String,
        ) =
            EventSheet().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putString(ARG_TOPIC, topic)
                    putString(ARG_DESC, desc)
                    putString(ARG_PERIOD, period)
                    putString(ARG_IMG, image)
                }
            }
    }
}