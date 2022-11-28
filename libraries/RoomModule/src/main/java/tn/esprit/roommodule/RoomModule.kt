package tn.esprit.roommodule

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tn.esprit.roommodule.dao.BookmarksDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): NebulaGamingDatabase =
        NebulaGamingDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideBookmarksDao(database: NebulaGamingDatabase): BookmarksDao = database.bookmarksDao()
}