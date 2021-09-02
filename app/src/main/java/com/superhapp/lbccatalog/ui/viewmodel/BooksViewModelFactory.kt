package com.superhapp.lbccatalog.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.superhapp.lbccatalog.data.datasource.BooksDataSource
import javax.inject.Inject

class BooksViewModelFactory @Inject constructor(private val datasource: BooksDataSource): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(BooksViewModel::class.java)) {
            BooksViewModel(this.datasource) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}