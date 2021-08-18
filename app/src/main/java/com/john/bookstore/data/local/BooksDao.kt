package com.john.bookstore.data.local

import android.arch.paging.DataSource
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(book: Book?)

    @Query("DELETE FROM book")
    fun deleteBooks()

    @Query("SELECT * FROM book")
    fun getSearchBooks(): DataSource.Factory<Int?, Book?>?

    @Query("SELECT * FROM detailbook WHERE isbn13 = :isbn13")
    fun getDetailBook(isbn13: String?): DetailBook?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(book: DetailBook?)

    @Query("UPDATE detailbook SET disableHistory= 1 WHERE isbn13 = :isbn13")
    fun deleteHistory(isbn13: String?)

    @Query("UPDATE detailbook SET memo=:memo WHERE isbn13 = :isbn13")
    fun setMemo(memo: String?, isbn13: String?)

    @Query("UPDATE detailbook SET isLiked=:isLike WHERE isbn13 = :isbn13")
    fun setLike(isLike: Boolean?, isbn13: String?)

    @Query("SELECT * FROM detailbook WHERE isLiked = 1 ORDER BY price ASC")
    fun getFavoriteOrderByPrice(): DataSource.Factory<Int?, DetailBook?>?

    @Query("SELECT * FROM detailbook WHERE isLiked = 1 ORDER BY rating DESC")
    fun getFavoriteOrderByRating(): DataSource.Factory<Int?, DetailBook?>?

    @Query("SELECT * FROM detailbook WHERE isLiked = 1 ORDER BY year DESC")
    fun getFavoriteOrderByPublished(): DataSource.Factory<Int?, DetailBook?>?

    @Query("SELECT * FROM detailbook WHERE disableHistory = 0")
    fun getHistoryBooks(): DataSource.Factory<Int?, DetailBook?>?

    // Search Keyword
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(keyword: SearchKeyword?)

    @Query("DELETE FROM searchkeyword WHERE text = :text")
    fun deleteSearchKeyword(text: String?)

    @Query("SELECT * FROM searchkeyword")
    fun getSearchKeywords(): LiveData<List<SearchKeyword?>?>?
}