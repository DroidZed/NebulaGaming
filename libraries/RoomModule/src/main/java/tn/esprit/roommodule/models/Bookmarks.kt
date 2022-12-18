package tn.esprit.roommodule.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bookmarks(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    val title: String,
    val link: String,
    val description: String,
    val image: String,
    val userId: String
)
