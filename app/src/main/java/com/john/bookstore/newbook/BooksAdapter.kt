package com.john.bookstore.newbook

import android.content.Intent
import android.net.Uri
import android.widget.TextView
import com.john.bookstore.R
import com.john.bookstore.base.BaseRecyclerViewAdapter
import com.john.bookstore.base.DataBindingViewHolder
import com.john.bookstore.data.local.Book

class BooksAdapter(callback: (book: Book) -> Unit): BaseRecyclerViewAdapter<Book>(callback) {
    override fun getLayoutRes(viewType: Int): Int = R.layout.item_book

    override fun onBindViewHolder(holder: DataBindingViewHolder<Book>, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.itemView.findViewById<TextView>(R.id.toWeb)?.setOnClickListener {
            val book = getItem(position)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(book.link))
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            holder.itemView.context.startActivity(intent)
        }
    }
}