package com.john.bookstore.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

import com.google.gson.annotations.SerializedName

@Entity
class Book(
    @PrimaryKey
    @NotNull
    @SerializedName("isbn13")
    val isbn13: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("url")
    val url: String
)