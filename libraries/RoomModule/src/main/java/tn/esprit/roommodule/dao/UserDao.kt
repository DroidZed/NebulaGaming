package tn.esprit.roommodule.dao

import androidx.room.Query
import androidx.room.Transaction
import tn.esprit.roommodule.models.UserAndBookmarks
import tn.esprit.roommodule.models.UserProfile

interface UserDao: EntityDao<UserProfile> {

    @Transaction
    @Query("SELECT * FROM UserProfile")
    fun getUserWithBookmarks(userId: String): List<UserAndBookmarks>

}