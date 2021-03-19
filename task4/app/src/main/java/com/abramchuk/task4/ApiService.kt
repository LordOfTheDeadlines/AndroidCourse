package com.abramchuk.task4

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {

    @GET("objects/{id}")
    suspend fun getArtObject(@Path("id") id: Int) : Response<ArtObject>

    @GET("departments")
    suspend fun getDepartments() :  Response<DepartmentsList>

    companion object {
        const val API_URL = "https://collectionapi.metmuseum.org/public/collection/v1/"

        fun instance() = Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
    }
}