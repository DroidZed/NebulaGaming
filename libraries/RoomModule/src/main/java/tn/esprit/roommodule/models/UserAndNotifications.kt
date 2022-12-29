package tn.esprit.roommodule.models

import androidx.room.Embedded
import androidx.room.Relation


data class UserAndNotifications(
    @Embedded val user: UserProfile?,
    @Relation(
        parentColumn = "_id",
        entityColumn = "userId"
    ) val notifications: List<Notification>
)
