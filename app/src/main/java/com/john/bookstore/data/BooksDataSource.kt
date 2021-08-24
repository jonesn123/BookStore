package com.john.bookstore.data

import com.john.bookstore.data.local.Book
import com.john.bookstore.data.local.DetailBook
import  com.john.bookstore.data.dto.Result
import com.john.bookstore.data.remote.SearchBooksResponse

interface BooksDataSource {
    suspend fun getBooks(): Result<List<Book>>
    suspend fun getSearchBooks(query: String, page: Int): Result<SearchBooksResponse>
    suspend fun getBookInfo(isbn13: String): Result<DetailBook>
    suspend fun getFavoriteBook(order: ORDER): Result<List<DetailBook>>
    suspend fun getHistories(): Result<List<DetailBook>>
    suspend fun addHistory(detailBook: DetailBook)
    suspend fun deleteAllHistory()
    suspend fun setFavorite(isList: Boolean, isbn13: String)
    suspend fun saveMemo(memo: String, isbn13: String)
}

enum class ORDER {
    RATING, PRICE, PUBLISHED
}