package tn.esprit.roommodule.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserProfile(
    @PrimaryKey val _id: String,
    val name: String,
    val photo: String,
    val email: String,
    val phone: String,
    val role: Int,
    val level: Int,
    val status: Int
)
