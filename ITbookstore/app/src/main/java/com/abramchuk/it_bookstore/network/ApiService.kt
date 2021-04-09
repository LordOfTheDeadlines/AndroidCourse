package com.abramchuk.it_bookstore.network

import com.abramchuk.it_bookstore.models.Book
import com.abramchuk.it_bookstore.models.BookInfo
import com.abramchuk.it_bookstore.models.BooksList
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("new")
    suspend fun getNewBooks(): Response<BooksList>

    @GET("search/{inputStr}/{page}")
    suspend fun searchBooks(@Path("inputStr")inputStr: String,
                           @Path("page")page:String): Response<BooksList>

    @GET("books/{id}")
    suspend fun getBookByISBN(@Path("id") id: String) : Response<BookInfo>


    companion object {
        const val API_URL = "https://api.itbook.store/1.0/"

        fun instance() = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}