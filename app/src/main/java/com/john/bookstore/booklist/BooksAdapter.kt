package com.john.bookstore.booklist

import com.john.bookstore.R
import com.john.bookstore.base.BaseRecyclerViewAdapter
import com.john.bookstore.data.local.DetailBook

class BooksAdapter(callback: (book: DetailBook) -> Unit): BaseRecyclerViewAdapter<DetailBook>(callback) {
    override fun getLayoutRes(viewType: Int): Int = R.layout.item_detailbook
}