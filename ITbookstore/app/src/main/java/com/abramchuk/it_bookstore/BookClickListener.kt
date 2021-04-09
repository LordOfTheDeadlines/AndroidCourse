package com.abramchuk.it_bookstore

import com.abramchuk.it_bookstore.models.Book

interface BookClickListener {
    fun onCellClickListener(item: Book, position: Int)
}