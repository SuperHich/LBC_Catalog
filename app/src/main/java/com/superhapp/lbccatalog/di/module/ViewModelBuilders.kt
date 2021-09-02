package com.superhapp.lbccatalog.di.module

import androidx.lifecycle.ViewModelProvider
import com.superhapp.lbccatalog.ui.viewmodel.BooksViewModelFactory
import dagger.Binds
import dagger.Module

@Module
internal abstract class ViewModelBuilders {

    @Binds
    internal abstract fun bindFactory(factory: BooksViewModelFactory): ViewModelProvider.Factory
}