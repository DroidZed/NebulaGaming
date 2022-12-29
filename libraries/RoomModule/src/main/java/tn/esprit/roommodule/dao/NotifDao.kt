package tn.esprit.roommodule.dao

import androidx.room.Dao
import tn.esprit.roommodule.models.Notification

@Dao
interface NotifDao : EntityDao<Notification>