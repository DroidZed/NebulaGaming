package tn.esprit.roommodule.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Wishlist(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    val name: String,
    val price: Float,
    val image: String?,
    val idProd: String,
    val userId: String
)
