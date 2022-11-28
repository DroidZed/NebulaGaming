package tn.esprit.nebulagaming.viewmodels

import android.icu.text.SimpleDateFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor() : DefaultViewModel() {

    fun getCurrentMonth(): String? = SimpleDateFormat("MM", Locale.ENGLISH).format(Date())

}