package tn.esprit.roommodule.repo

import tn.esprit.roommodule.dao.BookmarksDao
import tn.esprit.roommodule.models.Bookmarks
import javax.inject.Inject

class BookmarkRepo @Inject constructor(private val bookmarksDao: BookmarksDao) {

    /**
     * > The function `bookmarkArticle` takes an `ArticleEntity` as a parameter and calls the
     * `create` function on the `bookmarksDao` object
     *
     * @param article Bookmarks - The article to be bookmarked
     */
    fun bookmarkArticle(article: Bookmarks) {
        bookmarksDao.create(article)
    }


    /**
     * > The function removes an article from the bookmarks table
     *
     * @param article Bookmarks - The article to be removed from the bookmarks.
     */
    fun removeFromBookmarks(article: Bookmarks) {
        bookmarksDao.delete(article)
    }
}