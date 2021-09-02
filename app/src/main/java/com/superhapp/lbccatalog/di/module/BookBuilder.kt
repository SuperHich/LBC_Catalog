package com.superhapp.lbccatalog.di.module

import androidx.lifecycle.ViewModel
import com.superhapp.lbccatalog.di.ViewModelKey
import com.superhapp.lbccatalog.ui.BooksActivity
import com.superhapp.lbccatalog.ui.viewmodel.BooksViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [BookModule::class])
internal abstract class BookBuilder {

    @ContributesAndroidInjector
    internal abstract fun booksActivity(): BooksActivity

    @Binds
    @IntoMap
    @ViewModelKey(BooksViewModel::class)
    abstract fun bindBooksActivityViewModel(viewModel: BooksViewModel): ViewModel
}