package com.john.bookstore.booklist

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.john.bookstore.base.BaseViewModel
import com.john.bookstore.data.BooksRepository
import com.john.bookstore.data.ORDER
import com.john.bookstore.data.dto.Result
import com.john.bookstore.data.local.DetailBook
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    app: Application,
    private val repository: BooksRepository
) : BaseViewModel(app) {

    val books = MutableLiveData<List<DetailBook>>()

    fun loadBooks(order: ORDER) {
        showLoading.value = true
        viewModelScope.launch {
            val result = repository.getFavoriteBook(order)
            showLoading.postValue(false)
            when(result) {
                is Result.Success<*> -> {
                    val bookList = ArrayList<DetailBook>()
                    bookList.addAll((result.data as List<DetailBook>))
                    books.value = bookList
                }
                is Result.Error -> showErrorMessage.value = result.message
            }
            invalidateShowNoData()
        }
    }

    fun loadHistoryBooks() {
        showLoading.value = true
        viewModelScope.launch {
            val result = repository.getHistories()
            showLoading.value = false
            when(result) {
                is Result.Success<*> -> {
                    val bookList = ArrayList<DetailBook>()
                    bookList.addAll(result.data as List<DetailBook>)
                    books.value = bookList
                }
                is Result.Error -> showErrorMessage.value = result.message
            }
            invalidateShowNoData()
        }
    }

    private fun invalidateShowNoData() {
        showNoData.value = books.value == null || books.value!!.isEmpty()
    }

    fun deleteAllHistory() {
        viewModelScope.launch {
            repository.deleteAllHistory()
            loadHistoryBooks()
        }
    }
}