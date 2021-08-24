package com.john.bookstore.data.local

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.google.gson.annotations.SerializedName

@Entity(tableName = "detailbook")
data class DetailBook(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "isbn13")
    val isbn13: String,
    @ColumnInfo(name = "error")
    val error: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "authors")
    val authors: String,
    @ColumnInfo(name = "publisher")
    val publisher: String,
    @ColumnInfo(name = "language")
    val language: String,
    @ColumnInfo(name = "isbn10")
    val isbn10: String,
    @ColumnInfo(name = "pages")
    val pages: String,
    @ColumnInfo(name = "year")
    val year: String,
    @ColumnInfo(name = "rating")
    val rating: String,
    @ColumnInfo(name = "desc")
    val desc: String,
    @ColumnInfo(name = "price")
    val price: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "isLiked")
    var isLiked: Boolean = false,
    @ColumnInfo(name = "memo")
    var memo: String? = null,
    @ColumnInfo(name = "disableHistory")
    var disableHistory: Boolean = false
)