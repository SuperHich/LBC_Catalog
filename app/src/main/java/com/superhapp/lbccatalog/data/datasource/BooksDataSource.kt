package com.superhapp.lbccatalog.data.datasource

import com.superhapp.lbccatalog.data.model.Book
import com.superhapp.lbccatalog.data.model.Result

interface BooksDataSource {
    suspend fun fetchBooks() : Result<List<Book>>
}