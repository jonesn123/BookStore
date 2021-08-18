package com.john.bookstore.data.remote

import com.google.gson.annotations.SerializedName
import com.john.bookstore.data.local.Book

class NewBooksResponse(
    @SerializedName("error")
    val error: String,
    @SerializedName("total")
    val totalNum: String,
    @SerializedName("books")
    val books: List<Book>
)