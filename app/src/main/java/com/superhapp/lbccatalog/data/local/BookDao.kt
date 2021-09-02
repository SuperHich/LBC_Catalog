package com.superhapp.lbccatalog.data.local

import androidx.room.*
import com.superhapp.lbccatalog.data.model.Book

@Dao
interface BookDao {

    @Query("SELECT * FROM book")
    fun fetchAll(): List<Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(book: Book)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(books: List<Book>)

    @Update
    fun update(book: Book)

    @Query("DELETE FROM book")
    fun deleteAll()
}