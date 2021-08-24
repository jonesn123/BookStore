package com.john.bookstore.newbook

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.john.bookstore.base.BaseViewModel
import com.john.bookstore.data.BooksRepository
import com.john.bookstore.data.dto.Result
import com.john.bookstore.data.local.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewBooksViewModel @Inject constructor(
    app: Application,
    private val repository: BooksRepository
): BaseViewModel(app) {

    val books = MutableLiveData<List<Book>>()

    fun loadNewBooks() {
        showLoading.value = true
        viewModelScope.launch {
            val result = repository.getBooks()
            showLoading.value = false
            when(result) {
                is Result.Success<*> -> {
                    val bookList = ArrayList<Book>()
                    bookList.addAll((result.data) as List<Book>)
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
}