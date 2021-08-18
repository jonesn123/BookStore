package com.john.bookstore.data

import com.john.bookstore.data.local.Book
import com.john.bookstore.data.local.DetailBook
import  com.john.bookstore.data.dto.Result
import com.john.bookstore.data.local.SearchKeyword

interface BooksDataSource {
    suspend fun getBooks(): Result<List<Book>>
    suspend fun getSearchBooks(query: String, page: Int): Result<List<Book>>
    suspend fun getSearchKeywords(): Result<List<SearchKeyword>>
    suspend fun insertSearchKeyword(keyword: SearchKeyword)
    suspend fun deleteSearchKeyword(query: String)
    suspend fun getBookInfo(isbn13: String): Result<DetailBook>
    suspend fun getFavoriteBook(order: ORDER): Result<List<DetailBook>>
    suspend fun getHistories(): Result<List<DetailBook>>
    suspend fun addHistory(detailBook: DetailBook)
    suspend fun deleteHistory(isbn13: String)
    suspend fun setFavorite(isList: Boolean, isbn13: String)
}

enum class ORDER {
    RATING, PRICE, PUBLISHED
}