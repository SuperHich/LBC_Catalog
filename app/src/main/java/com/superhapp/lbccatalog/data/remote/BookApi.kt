package com.superhapp.lbccatalog.data.remote

import com.superhapp.lbccatalog.data.model.Book
import retrofit2.Call
import retrofit2.http.GET

interface BookApi {
    @GET("technical-test.json")
    fun fetchBooks(): Call<List<Book>>
}