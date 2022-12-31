package tn.esprit.roommodule.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import tn.esprit.roommodule.models.Notification

@Dao
interface NotifDao : EntityDao<Notification> {

    @Query("SELECT * FROM Notification WHERE data LIKE :data")
    suspend fun getByData(data: String): Notification

    @Query("SELECT COUNT(*) FROM Notification")
    fun countNotifications(): LiveData<Int>
}