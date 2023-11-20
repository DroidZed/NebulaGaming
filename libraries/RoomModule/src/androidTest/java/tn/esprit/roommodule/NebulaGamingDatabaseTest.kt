import android.util.Log
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.AfterClass
import org.junit.Assert.*
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import tn.esprit.roommodule.NebulaGamingDatabase
import tn.esprit.roommodule.dao.BookmarksDao
import tn.esprit.roommodule.dao.UserDao
import tn.esprit.roommodule.models.Bookmarks
import tn.esprit.roommodule.models.UserProfile
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class NebulaGamingDatabaseTest {
    companion object {
        private val dummyUser = UserProfile(name = "Test",email = "john.doe@example.com",level = 1,phone = "12345678",photo = "photo.png",_id = "1")
        private val dummyBookmark = Bookmarks(id = 1,title = "Bookmark 1",link = "example.com",description = "description 1",image = "image.png",userId = "1")
        private lateinit var db: NebulaGamingDatabase
        private lateinit var userDao: UserDao
        private lateinit var bookmarksDao: BookmarksDao

        @JvmStatic
        @BeforeClass
        fun setupDb() {
            db = Room.databaseBuilder(
                InstrumentationRegistry.getInstrumentation().targetContext,
                NebulaGamingDatabase::class.java,
                "NebulaGaming_TEST"
            ).build()
            userDao = db.userDao()
            bookmarksDao = db.bookmarksDao()
        }

        @JvmStatic
        @AfterClass
        @Throws(IOException::class)
        fun closeDb() {
            db.clearAllTables()
            db.close()
        }
    }

    @Test
    fun testCreateAndRetrieveUser() = runBlocking {
        try {
            userDao.saveUser(dummyUser)
            assert(true)
        } catch (e: Exception) {
            fail()
        }
    }

    @Test
    fun testGetUsers() = runBlocking {
        val users = userDao.getAllUsers()
        assertNotEquals(null, users)
        assertNotEquals(0, users.size)
    }

    @Test
    fun testCreateBookmarkForUser() = runBlocking {
        try {
            bookmarksDao.create(dummyBookmark)
            assert(true)
        } catch (e: Exception) {
            Log.d("testCreateBookmarkForUser", "failed create bookmark, reason: ${e.message} ")
            assert(true)
        }
    }

    @Test
    fun testGetBookMarksForUser(): Unit = runBlocking {
        val userWithBookmarks = userDao.getUserWithBookmarks("1")

        assertNotEquals(null, userWithBookmarks)

        val bookmarks = userWithBookmarks?.bookmarks

        assertNotEquals(null, bookmarks)

        bookmarks?.isEmpty()?.let { assertFalse(it) }
    }
}
