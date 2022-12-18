package tn.esprit.apimodule.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val id: Int? = null,
    val title: String? = null,
    val link: String? = null,
    val pubDate: String? = null,
    val image: String? = null,
    val content: String? = null,
    val tags: List<String>? = null
) : Parcelable
