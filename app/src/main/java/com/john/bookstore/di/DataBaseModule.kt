package com.john.bookstore.di

import android.content.Context
import com.john.bookstore.data.local.BooksDao
import com.john.bookstore.data.local.BooksDataBase
import com.john.bookstore.data.local.LocalDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): BooksDataBase =
        LocalDB.createDatabase(appContext)

    @Provides
    fun provideBooksDao(booksDataBase: BooksDataBase): BooksDao = booksDataBase.booksDao()

}