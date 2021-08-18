package com.john.bookstore.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookDao::class], version = 1, exportSchema = false)
abstract class BookDataBase: RoomDatabase() {
    abstract fun bookDao(): BookDao
}