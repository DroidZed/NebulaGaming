package tn.esprit.nebulagaming.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import tn.esprit.apimodule.models.User
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.databinding.LayoutOneCommentBinding
import tn.esprit.nebulagaming.viewmodels.ProfileViewModel

class ListUserAdapter(private val data: MutableList<User>) :
    RecyclerView.Adapter<ListUserAdapter.ViewHolder>() {
    private val profVM: ProfileViewModel?=null


    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    fun addAll(users: MutableList<User>) {
        data.addAll(users)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_oneuser, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = data[position]
        holder.id = user._id
        holder.namecarduser.text = user.name
        holder.cardemailuser.text = user.email
        holder.phoneusercrd.text = user.phone
        holder.roleUsercard.text = user.role.toString()
        holder.levelusercard.text = user.level.toString()
        holder.statususer.text = user.status.toString()
        holder.createsatcard.text = user.createdAd.toString()
        if (user.status == 0) {
            holder.ActDesactbutton.text = "Enable"
            holder.ActDesactbutton.setOnClickListener {
                Log.e("id:", user._id)
                profVM!!.EnableuserId(holder.itemView.context, user._id)
            }
        } else if (user.status == 1) {
            holder.ActDesactbutton.text = "Disable"
            holder.ActDesactbutton.setOnClickListener {
                Log.e("id:", user._id)
            }
        }

        if(user.role == 0){
            holder.roleUsercard.text = "Admin"}
        else if(user.role == 1){
            holder.roleUsercard.text = "User"}
        else if(user.role == 2){
            holder.roleUsercard.text = "Company"}





    }


    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id = ""
        val photocardusert = itemView.findViewById<ShapeableImageView>(R.id.photocardusert)
        val roleUsercard = itemView.findViewById<TextView>(R.id.roleUsercard)
        val namecarduser = itemView.findViewById<TextView>(R.id.namecarduser)
        val cardemailuser = itemView.findViewById<TextView>(R.id.cardemailuser)
        val ActDesactbutton = itemView.findViewById<TextView>(R.id.ActDesactbutton)
        val phoneusercrd = itemView.findViewById<TextView>(R.id.phoneusercrd)
        val levelusercard = itemView.findViewById<TextView>(R.id.levelusercard)
        val statususer = itemView.findViewById<TextView>(R.id.statususer)
        val createsatcard = itemView.findViewById<TextView>(R.id.createsatcard)

    }
}