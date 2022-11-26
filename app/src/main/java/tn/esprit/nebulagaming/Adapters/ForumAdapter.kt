package tn.esprit.nebulagaming.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import tn.esprit.nebulagaming.DetailPostActivity
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.data.ForumPost

class ForumAdapter(
    private val list: List<ForumPost>,
    private val context: Context,
) : RecyclerView.Adapter<ForumAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = list[position]

        holder.idPost = post.idPost
        holder.postUser.text = post.nameUser
        holder.postDate.text = post.datePost
        holder.postText.text = post.descriptionPost
        holder.postLikes.text = post.likesPost
        holder.postSaved.text = post.savedPost
        holder.postPhoto.setImageResource(post.photoPost)
        holder.postUserPhoto.setImageResource(post.photouser)

        val toPass = Bundle()
        toPass.putInt("idPost", post.idPost)
        toPass.putString("postUser", post.nameUser)
        toPass.putString("postDate", post.datePost)
        toPass.putString("postText", post.descriptionPost)
        toPass.putString("postLikes", post.likesPost)
        toPass.putString("postSaved", post.savedPost)
        toPass.putInt("postPhoto", post.photoPost)
        toPass.putInt("postUserPhoto", post.photouser)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailPostActivity::class.java)
            intent.putExtras(toPass)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var idPost = 0
        val postText = itemView.findViewById<TextView>(R.id.postText)
        val postDate = itemView.findViewById<TextView>(R.id.timepost)
        val postLikes = itemView.findViewById<TextView>(R.id.likepost)
        val postSaved = itemView.findViewById<TextView>(R.id.savepost)
        val postUser = itemView.findViewById<TextView>(R.id.nameuserpost)
        val postUserPhoto = itemView.findViewById<ShapeableImageView>(R.id.photoprofdetailuser)
        val postPhoto = itemView.findViewById<ShapeableImageView>(R.id.post_image)
        override fun onClick(v: View?) {
            val position = adapterPosition
        }
    }
}
