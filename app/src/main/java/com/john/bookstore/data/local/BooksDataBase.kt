package com.john.bookstore.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Book::class, DetailBook::class], version = 1, exportSchema = false)
abstract class BooksDataBase: RoomDatabase() {
    abstract fun booksDao(): BooksDao
}