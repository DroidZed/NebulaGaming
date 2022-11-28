package tn.esprit.nebulagaming.viewholders

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import tn.esprit.nebulagaming.DetailPostActivity
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.data.ForumPost
import tn.esprit.nebulagaming.data.ParcelablePostData
import tn.esprit.shared.Consts.POST_DATA

class ForumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    private var postText: TextView? = null
    private var postDate: TextView? = null
    private var postLikes: TextView? = null
    private var postSaved: TextView? = null
    private var postUser: TextView? = null
    private var postUserPhoto: ShapeableImageView? = null
    private var postPhoto: ShapeableImageView? = null

    init {
        postText = itemView.findViewById(R.id.postText)
        postDate = itemView.findViewById(R.id.timepost)
        postLikes = itemView.findViewById(R.id.likepost)
        postSaved = itemView.findViewById(R.id.savepost)
        postUser = itemView.findViewById(R.id.nameuserpost)
        postUserPhoto = itemView.findViewById(R.id.photoprofdetailuser)
        postPhoto = itemView.findViewById(R.id.post_image)
    }

    fun bind(post: ForumPost, context: Context) {

        postUser?.text = post.nameUser
        postDate?.text = post.datePost
        postText?.text = post.descriptionPost
        postLikes?.text = post.likesPost
        postSaved?.text = post.savedPost
        postPhoto?.setImageResource(post.photoPost)
        postUserPhoto?.setImageResource(post.photouser)

        itemView.setOnClickListener {
            Intent(context, DetailPostActivity::class.java).apply {
                putExtra(
                    POST_DATA, ParcelablePostData(
                        "${post.idPost}",
                        post.nameUser,
                        post.datePost,
                        post.descriptionPost,
                        post.likesPost,
                        post.savedPost,
                        post.photoPost,
                        post.photouser,
                    )
                )
                startActivity(context, this, null)
            }
        }
    }

}