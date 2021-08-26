package com.john.bookstore.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import org.jetbrains.annotations.NotNull

@Entity(tableName = "book")
data class Book(
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "isbn13")
    val isbn13: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "subtitle")
    val subtitle: String,
    @ColumnInfo(name = "price")
    val price: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "link")
    @Json(name = "url")
    val link: String
)