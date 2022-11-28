package tn.esprit.nebulagaming.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParcelablePostData(
    val idPost: String,
    val postUser: String,
    val postDate: String,
    val postText: String,
    val postLikes: String,
    val postSaved: String,
    val postPhoto: Int,
    val postUserPhoto: Int
): Parcelable
