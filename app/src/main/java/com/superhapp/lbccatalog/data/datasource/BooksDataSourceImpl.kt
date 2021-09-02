package com.superhapp.lbccatalog.data.datasource

import com.superhapp.lbccatalog.data.local.LBCDatabase
import com.superhapp.lbccatalog.data.model.Book
import com.superhapp.lbccatalog.data.model.Result
import com.superhapp.lbccatalog.data.remote.BookApi
import com.superhapp.lbccatalog.di.module.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException

class BooksDataSourceImpl(private val bookApi: BookApi,
                          private val database: LBCDatabase,
                          @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BooksDataSource {

    override suspend fun fetchBooks(): Result<List<Book>> {
        var error : Exception? = null
        var databaseResult : List<Book>

        withContext(ioDispatcher) {
            try {
                // Fetch data from Api than persist it to the Database
                fetchBooksFromApi()
            } catch (exception: Exception) {
                error = exception
            }

            databaseResult = fetchBooksFromDatabase()
        }

        return when {
            databaseResult.isNotEmpty() -> {
                Result.Success(databaseResult)
            }
            databaseResult.isEmpty() -> {
                Result.Error(Throwable("No local data"))
            }
            error != null -> {
                Result.Error(error)
            }
            else -> {
                Result.Error(Throwable("Unknown error"))
            }
        }
    }

    private fun fetchBooksFromDatabase(): List<Book> {
        // Fetch data from database
        return database.bookDao().fetchAll()
    }

    private fun fetchBooksFromApi() {
        return try {
            val result = bookApi.fetchBooks().execute()
            if (result.isSuccessful) {
                database.bookDao().insertAll(result.body() ?: emptyList())
            } else {
                throw Throwable("Network error")
            }
        } catch (exception: IOException) {
            throw exception
        }
    }


}