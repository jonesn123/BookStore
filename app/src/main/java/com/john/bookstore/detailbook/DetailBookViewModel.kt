package com.john.bookstore.detailbook

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.john.bookstore.base.BaseViewModel
import com.john.bookstore.data.BooksRepository
import com.john.bookstore.data.dto.Result
import com.john.bookstore.data.local.DetailBook
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailBookViewModel @Inject constructor(
    app: Application,
    private val repository: BooksRepository
): BaseViewModel(app) {

    private val _book = MutableLiveData<DetailBook>()
    val book: LiveData<DetailBook> = _book

    private val _sendNotify = MutableLiveData<DetailBook>()
    val sendNotify: LiveData<DetailBook> = _sendNotify

    fun fetchBookInformation(isbn13: String) {
        showLoading.value = true
        viewModelScope.launch {
            val result = repository.getBookInfo(isbn13)
            showLoading.value = false
            when(result) {
                is Result.Success<*> -> {
                    _book.value = result.data as DetailBook
                }
                is Result.Error -> showErrorMessage.value = result.message
            }
        }
    }

    fun toggleLiked(isbn13: String) {
        val book = _book.value
        if (book != null) {
            if (!book.isLiked) {
                _sendNotify.value = book
            }
            viewModelScope.launch {
                repository.setFavorite(!book.isLiked, isbn13)
                fetchBookInformation(isbn13)
            }
        }
    }

    fun saveMemo(memo: String, isbn13: String) = viewModelScope.launch {
        repository.saveMemo(memo, isbn13)
    }
}