package tn.esprit.roommodule.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

interface EntityDao<T> {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun create(e: T)

    @Insert
    suspend fun addAll(e: List<T>)

    @Update
    suspend fun update(e: T)

    @Delete
    suspend fun delete(e: T)
}
