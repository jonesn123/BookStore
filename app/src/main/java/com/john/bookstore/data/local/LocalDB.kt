package com.john.bookstore.data.local

import android.content.Context
import androidx.room.Room

object LocalDB {
    fun createDatabase(context: Context): BooksDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            BooksDataBase::class.java, "book.db"
        ).build()
    }
}