package com.example.anchorbooks.Model.Remote

import com.example.anchorbooks.Model.Local.Book
import com.example.anchorbooks.Model.Local.DetailBook
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiApp {

    @GET("books/")
    suspend fun fetchAllBooks(): Response<List<Book>>

    @GET("bookDetail/{id}")
    suspend fun fetchOneBook(@Path("id") id: Int): Response<DetailBook>





}