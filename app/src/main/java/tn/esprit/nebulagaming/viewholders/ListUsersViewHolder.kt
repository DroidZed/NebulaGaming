package tn.esprit.nebulagaming.viewholders

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import tn.esprit.apimodule.models.User
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.viewmodels.ProfileViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ListUsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val profVM: ProfileViewModel?=null

    var photocardusert: ShapeableImageView? = null
    var photourl: String? = null
    var roleUsercard: TextView? = null
    var namecarduser: TextView? = null
    var cardemailuser: TextView? = null
    var ActDesactbutton: Button? = null
    var phoneusercrd: TextView? = null
    var levelusercard: TextView? = null
    var statususer :TextView? = null
    var createsatcard: TextView? = null



    init {

        photocardusert = itemView.findViewById(R.id.photocardusert)
        roleUsercard = itemView.findViewById(R.id.roleUsercard)
        namecarduser = itemView.findViewById(R.id.namecarduser)
        cardemailuser = itemView.findViewById(R.id.cardemailuser)
        ActDesactbutton = itemView.findViewById(R.id.ActDesactbutton)
        phoneusercrd = itemView.findViewById(R.id.phoneusercrd)
        levelusercard = itemView.findViewById(R.id.levelusercard)
        statususer = itemView.findViewById(R.id.statususer)
        createsatcard = itemView.findViewById(R.id.createsatcard)

    }

    fun bind(UserProfileResponse: User) {
        photourl = UserProfileResponse.photo
        roleUsercard!!.text = UserProfileResponse.role.toString()
        namecarduser!!.text = UserProfileResponse.name
        cardemailuser!!.text = UserProfileResponse.email
        phoneusercrd!!.text = UserProfileResponse.phone
        levelusercard!!.text = UserProfileResponse.level.toString()
        statususer!!.text = UserProfileResponse.status.toString()
        createsatcard!!.text =parseDateToddMMyyyy( UserProfileResponse.createdAd.toString())
        if (UserProfileResponse.status == 0) {
            ActDesactbutton!!.text = "Enable"
            ActDesactbutton!!.setOnClickListener { profVM!!.EnableuserId(itemView.context,UserProfileResponse._id) }
        } else if (UserProfileResponse.status == 1){
            ActDesactbutton!!.text = "Disable"

            ActDesactbutton!!.setOnClickListener {  Log.e("id:",UserProfileResponse._id) }

        }
        if(UserProfileResponse.role == 0){
            roleUsercard!!.text = "Admin"
        }else if(UserProfileResponse.role == 1){
            roleUsercard!!.text = "User"}
        else if(UserProfileResponse.role==2){
            roleUsercard!!.text = "Company"
        }
        if (UserProfileResponse.status == 0) {
            statususer!!.text = "Disabled"
        } else {
            statususer!!.text = "Enabled"
        }
        

    }


    private fun parseDateToddMMyyyy(time: String): String? {
        var inputPattern = "EEE MMM dd HH:mm:ss zzz yyyy"
        var outputPattern = "dd/MM/yyyy HH:mm"
        var inputFormat = SimpleDateFormat(inputPattern)
        var outputFormat = SimpleDateFormat(outputPattern)
        var date: Date? = null
        var str: String? = null
        try {
            date = inputFormat.parse(time)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return str
    }

}