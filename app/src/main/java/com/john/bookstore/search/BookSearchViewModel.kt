package com.john.bookstore.search

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.john.bookstore.base.BaseViewModel
import com.john.bookstore.data.BooksRepository
import com.john.bookstore.data.dto.Result
import com.john.bookstore.data.local.Book
import com.john.bookstore.data.remote.SearchBooksResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookSearchViewModel @Inject constructor(
    app: Application,
    private val repository: BooksRepository
) : BaseViewModel(app) {

    val books = MutableLiveData<List<Book>>()
    val isLastPage = MutableLiveData<Boolean>()

    fun searchBook(query: String, page: Int) {
        showLoading.value = true
        viewModelScope.launch {
            val result = repository.getSearchBooks(
                query, page
            )
            showLoading.value = false
            when (result) {
                is Result.Success<*> -> {
                    val response = result.data as SearchBooksResponse
                    if (response.page != null && response.totalNum != null) {
                        isLastPage.value =
                            response.page.toInt().times(10) * 10 >= response.totalNum.toInt()
                    }

                    val bookList = ArrayList<Book>()
                    bookList.addAll(response.books)
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