package tn.esprit.roommodule.models

import androidx.room.Embedded
import androidx.room.Relation

data class UserAndBookmarks(
    @Embedded val user: UserProfile?,
    @Relation(
        parentColumn = "_id",
        entityColumn = "userId"
    ) val bookmarks: List<Bookmarks>?
)