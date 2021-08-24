package com.john.bookstore.data.remote

import com.google.gson.annotations.SerializedName
import com.john.bookstore.data.local.Book

class SearchBooksResponse(
    @SerializedName("error")
    val error: String,
    @SerializedName("total")
    val totalNum: String? = null,
    @SerializedName("page")
    val page: String? = null,
    @SerializedName("books")
    val books: List<Book>
)