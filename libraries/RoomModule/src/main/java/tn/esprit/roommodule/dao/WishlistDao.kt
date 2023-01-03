package tn.esprit.roommodule.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import tn.esprit.roommodule.models.UserAndWishlist
import tn.esprit.roommodule.models.Wishlist

@Dao
interface WishlistDao : EntityDao<Wishlist>