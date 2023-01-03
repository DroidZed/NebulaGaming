package tn.esprit.roommodule.dao

import androidx.room.Dao
import androidx.room.Query
import tn.esprit.roommodule.models.Bookmarks

@Dao
interface BookmarksDao : EntityDao<Bookmarks>