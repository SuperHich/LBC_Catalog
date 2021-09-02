package com.superhapp.lbccatalog.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.superhapp.lbccatalog.data.datasource.BooksDataSource
import com.superhapp.lbccatalog.ui.model.Book
import com.superhapp.lbccatalog.data.model.Result
import com.superhapp.lbccatalog.ui.model.transform
import kotlinx.coroutines.launch
import javax.inject.Inject

class BooksViewModel @Inject constructor(private val dataSource: BooksDataSource)  : ViewModel() {

    private val _books = MutableLiveData<List<Book>>().apply { value = emptyList() }
    val books: LiveData<List<Book>> = _books

    private val _onMessageError = MutableLiveData<String>()
    val onMessageError: LiveData<String> = _onMessageError

    fun loadBooks() {
        viewModelScope.launch {
            when(val result = dataSource.fetchBooks()) {
                is Result.Success -> _books.value = transform(result.data)
                is Result.Error -> _onMessageError.value = result.throwable?.message
            }
        }
    }
}