package tn.esprit.roommodule.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface EntityDao<T> {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun create(e: T)

    @Insert
    fun addAll(e: List<T>)

    @Update
    fun update(e: T)

    @Delete
    fun delete(e: T)
}
