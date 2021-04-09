package com.abramchuk.it_bookstore.models

data class BooksList (
    val total: String,
    val page: String,
    val books: List<Book>
    )