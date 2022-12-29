package tn.esprit.roommodule

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tn.esprit.roommodule.dao.BookmarksDao
import tn.esprit.roommodule.dao.NotifDao
import tn.esprit.roommodule.dao.UserDao
import tn.esprit.roommodule.models.Bookmarks
import tn.esprit.roommodule.models.Notification
import tn.esprit.roommodule.models.UserProfile
import tn.esprit.roommodule.utils.ArrayListToJsonConverter
import tn.esprit.shared.Consts.DB_NAME
import tn.esprit.shared.Consts.DB_VERSION

@Database(
    entities = [Bookmarks::class, UserProfile::class, Notification::class],
    version = DB_VERSION
)
abstract class NebulaGamingDatabase : RoomDatabase() {

    abstract fun bookmarksDao(): BookmarksDao
    abstract fun userDao(): UserDao
    abstract fun notifDao(): NotifDao

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
                ).build()

                return INSTANCE!!
            }
        }
    }
}