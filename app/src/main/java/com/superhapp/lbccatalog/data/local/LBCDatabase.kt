package com.superhapp.lbccatalog.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.superhapp.lbccatalog.data.model.Book

@Database(entities = [Book::class], version = 3, exportSchema = false)
abstract class LBCDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao
}