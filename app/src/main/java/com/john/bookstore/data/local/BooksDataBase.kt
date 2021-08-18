package com.john.bookstore.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BooksDao::class], version = 1, exportSchema = false)
abstract class BooksDataBase: RoomDatabase() {
    abstract fun bookDao(): BooksDao
}