package tn.esprit.roommodule

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tn.esprit.roommodule.dao.BookmarksDao
import tn.esprit.roommodule.models.Bookmarks
import tn.esprit.shared.Consts.DB_NAME
import tn.esprit.shared.Consts.DB_VERSION

@Database(
    entities = [Bookmarks::class],
    version = DB_VERSION,
    exportSchema = false
)
abstract class NebulaGamingDatabase : RoomDatabase() {

    abstract fun bookmarksDao(): BookmarksDao

    companion object {

        @Volatile
        private var INSTANCE: NebulaGamingDatabase? = null

        fun getInstance(context: Context): NebulaGamingDatabase {

            if (INSTANCE != null) {
                return INSTANCE!!
            }

            synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    NebulaGamingDatabase::class.java,
                    DB_NAME
                ).allowMainThreadQueries().build()

                return INSTANCE!!
            }
        }
    }
}