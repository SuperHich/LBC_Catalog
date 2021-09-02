package com.superhapp.lbccatalog

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.superhapp.lbccatalog.data.datasource.BooksDataSource
import com.superhapp.lbccatalog.data.model.Book
import com.superhapp.lbccatalog.data.model.Result
import com.superhapp.lbccatalog.ui.viewmodel.BooksViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class BooksViewModelTest {

    @Mock
    private lateinit var booksDataSource: BooksDataSource

    private lateinit var viewModel: BooksViewModel

    private lateinit var booksList: List<Book>

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        Dispatchers.setMain(testDispatcher)

        mockData()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `retrieve books and returns empty data`() = runBlockingTest {
        //given
        viewModel = BooksViewModel(booksDataSource)
        `when`(booksDataSource.fetchBooks()).thenReturn(Result.Success(emptyList()))

        //when
        viewModel.loadBooks()

        //than
        viewModel.books.observeForever {
            Assert.assertTrue(it?.size == 0)
        }
    }

    @Test
    fun `retrieve books and returns full data`() = runBlockingTest {
        //given
        viewModel = BooksViewModel(booksDataSource)
        `when`(booksDataSource.fetchBooks()).thenReturn(Result.Success(booksList))

        //when
        viewModel.loadBooks()

        //then
        viewModel.books.observeForever {
            val expectedResult = it ?: emptyList()
            Assert.assertNotNull(it)
            Assert.assertTrue(expectedResult.size == 3)
            Assert.assertTrue(expectedResult[0].id == 1)
            Assert.assertTrue(expectedResult[0].albumId == 1)
            Assert.assertTrue(expectedResult[0].title == "book1")
        }
    }

    @Test
    fun `retrieve books and returns an error`() = runBlockingTest {
        //given
        viewModel = BooksViewModel(booksDataSource)
        `when`(booksDataSource.fetchBooks()).thenReturn(Result.Error(Throwable("Error")))

        //when
        viewModel.loadBooks()

        //Than
        viewModel.onMessageError.observeForever {
            Assert.assertNotNull(it)
            Assert.assertEquals(it, "Error")
        }
    }

    private fun mockData() {
        booksList = mutableListOf(
            Book(1, 1, "book1", "http://url/book1", "http://url/book1/thumb"),
            Book(2, 1, "book2", "http://url/book2", "http://url/book2/thumb"),
            Book(3, 2, "book3", "http://url/book3", "http://url/book3/thumb"))
    }
}