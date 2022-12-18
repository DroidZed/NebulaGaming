package tn.esprit.roommodule.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserProfile(
    @PrimaryKey(autoGenerate = false) val _id: String,
    var name: String,
    var photo: String,
    var email: String,
    var phone: String,
    var level: Int,
)