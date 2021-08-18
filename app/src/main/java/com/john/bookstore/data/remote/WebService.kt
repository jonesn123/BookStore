package com.john.bookstore.data.remote

import com.john.bookstore.data.local.DetailBook
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {

    @GET("new")
    suspend fun getNewBooks(): NewBooksResponse

    @GET("search/{query}/{page}")
    suspend fun getSearchBook(
        @Path("query") query: String,
        @Path("page") page: Int
    ): SearchBooksResponse

    @GET("books/{isbn13}")
    suspend fun getBookInformation(@Path("isbn13") isbn13: String): DetailBook
}