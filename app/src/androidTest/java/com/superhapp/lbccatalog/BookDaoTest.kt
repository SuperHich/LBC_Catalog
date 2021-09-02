package com.superhapp.lbccatalog

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.superhapp.lbccatalog.data.local.BookDao
import com.superhapp.lbccatalog.data.local.LBCDatabase
import com.superhapp.lbccatalog.data.model.Book
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class BookDaoTest {

    private lateinit var bookDao: BookDao
    private lateinit var db: LBCDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()

        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, LBCDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        bookDao = db.bookDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetBook() = runBlocking {
        val book = Book(1, 1,"book1", "http://url/book1", "http://url/book1/thumb")
        bookDao.insert(book)
        val allBooks = bookDao.fetchAll()
        assertEquals(allBooks[0].title, book.title)
    }

    @Test
    @Throws(Exception::class)
    fun getAllBooks() = runBlocking {
        val book1 = Book(1, 1,"book1", "http://url/book1", "http://url/book1/thumb")
        bookDao.insert(book1)
        val book2 = Book(2, 1,"book2", "http://url/book2", "http://url/book2/thumb")
        bookDao.insert(book2)
        val allBooks = bookDao.fetchAll()
        assertEquals(allBooks[0].title, book1.title)
        assertEquals(allBooks[1].title, book2.title)
    }

    @Test
    @Throws(Exception::class)
    fun deleteAllBooks() = runBlocking {
        val book1 = Book(1, 1,"book1", "http://url/book1", "http://url/book1/thumb")
        bookDao.insert(book1)
        val book2 = Book(2, 1,"book2", "http://url/book2", "http://url/book2/thumb")
        bookDao.insert(book2)
        bookDao.deleteAll()
        val allBooks = bookDao.fetchAll()
        assertTrue(allBooks.isEmpty())
    }
}