package tn.esprit.nebulagaming.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.models.ComentPost

class ComentAdapter(private val list: List<ComentPost>) : RecyclerView.Adapter<ComentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.onecomment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val coment = list[position]
        holder.idComent = coment.id
        holder.comentUser.text = coment.usernameComment
        holder.comentDate.text = coment.dateComment
        holder.comentText.text = coment.commentsPost
        holder.comentUserPhoto.setImageResource(coment.photoUserComment)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var idComent: Int = 0
        var comentUser: TextView = itemView.findViewById(R.id.usernamecomm)
        var comentDate: TextView = itemView.findViewById(R.id.commentdate)
        var comentText: TextView = itemView.findViewById(R.id.textcomment)
        var comentUserPhoto: ShapeableImageView = itemView.findViewById(R.id.photoprofComment)




    }
}
