package com.john.bookstore.data.local

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class SearchKeyword(
    @PrimaryKey
    @NonNull
    val text: String
)