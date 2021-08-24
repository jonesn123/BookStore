package com.john.bookstore.data.remote

import com.john.bookstore.data.local.Book

data class NewBooksResponse(
    val error: String,
    val total: String,
    val books: List<Book>
)