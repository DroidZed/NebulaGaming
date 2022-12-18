package tn.esprit.nebulagaming.viewmodels

import android.content.Context
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import tn.esprit.apimodule.NetworkClient
import tn.esprit.apimodule.models.GenericResp
import tn.esprit.apimodule.utils.ResponseConverter
import tn.esprit.nebulagaming.utils.Resource
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor() : DefaultViewModel() {

    //   fun getMonthFromDate(d: Date? = null): String = SimpleDateFormat("MM", Locale.ENGLISH).format(d?: Date())

    fun parseDate(date: String): LocalDateTime? =
        LocalDateTime.parse(date)

    suspend fun fetchEventsOfTheMonthByYear(context: Context, month: Int?, year: Int?) = liveData {

        val client = NetworkClient(context)

        val service = client.getEventService()

        emit(Resource.loading(data = null))

        try {
            val response = service.getOfMonthAndYear(month, year)

            if (response.isSuccessful) emit(Resource.success(data = response.body()))
            else
                emit(
                    Resource.error(
                        null,
                        ResponseConverter.convert<GenericResp>(
                            response.errorBody()!!.string()
                        ).data?.error!!
                    )
                )

        } catch (e: Exception) {
            emit(
                Resource.error(
                    data = null,
                    message = e.message ?: "Unable to fetch today's event, please try again later."
                )
            )
        }
    }

    /*
        fun fetchEventOfTheDay(context: Context) = liveData {

            val client = NetworkClient(context)

            val service = client.getEventService()

            emit(Resource.loading(data = null))

            try {
                val response = service.getOfToday()

                if (response.isSuccessful) emit(Resource.success(data = response.body()))
                else
                    emit(
                        Resource.error(
                            null,
                            ResponseConverter.convert<GenericResp>(
                                response.errorBody()!!.string()
                            ).data?.error!!
                        )
                    )

            } catch (e: Exception) {
                emit(
                    Resource.error(
                        data = null,
                        message = e.message ?: "Unable to fetch today's event, please try again later."
                    )
                )
            }
        }
    */
}