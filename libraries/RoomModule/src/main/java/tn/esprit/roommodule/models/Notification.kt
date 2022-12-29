package tn.esprit.roommodule.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notification(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    val title: String,
    val body: String,
    val data: String,
    val userId: String,
    val src: String
)
