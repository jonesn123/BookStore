package com.john.bookstore.data.local

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.google.gson.annotations.SerializedName

@Entity
class DetailBook(
    @PrimaryKey
    @NonNull
    @SerializedName("isbn13")
    val isbn13: String,
    @SerializedName("error")
    val error: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("authors")
    val authors: String,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("isbn10")
    val isbn10: String,
    @SerializedName("pages")
    val pages: String,
    @SerializedName("year")
    val year: String,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("url")
    val storeUrl: String,
    val isLiked: Boolean,
    val memo: String,
    val disableHistory: Boolean
)