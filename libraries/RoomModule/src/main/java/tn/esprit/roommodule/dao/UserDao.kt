package tn.esprit.roommodule.dao

import androidx.room.*
import tn.esprit.roommodule.models.UserAndBookmarks
import tn.esprit.roommodule.models.UserAndNotifications
import tn.esprit.roommodule.models.UserProfile

@Dao
interface UserDao : EntityDao<UserProfile> {

    @Transaction
    @Query("SELECT * FROM UserProfile WHERE _id = :userId")
    suspend fun getUserWithBookmarks(userId: String): UserAndBookmarks?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(e: UserProfile)

    @Transaction
    @Query("SELECT * FROM UserProfile WHERE _id = :userId")
    suspend fun getUserWithNotifications(userId: String): UserAndNotifications?
}