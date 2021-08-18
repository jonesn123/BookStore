package com.john.bookstore.data.local

import android.content.Context
import androidx.room.Room

object LocalDB {
    fun createBooksDao(context: Context): BooksDao {
        return Room.databaseBuilder(
            context.applicationContext,
            BooksDataBase::class.java, "book.db"
        ).build().bookDao()
    }
}