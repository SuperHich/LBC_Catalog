package com.superhapp.lbccatalog.di.module

import com.superhapp.lbccatalog.data.datasource.BooksDataSource
import com.superhapp.lbccatalog.data.datasource.BooksDataSourceImpl
import com.superhapp.lbccatalog.data.local.LBCDatabase
import com.superhapp.lbccatalog.data.remote.BookApi
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

@Module
internal class BookModule {

    @Provides
    fun provideBookApi(retrofit: Retrofit): BookApi =
        retrofit.create(BookApi::class.java)

    @Provides
    fun provideDataSource(api: BookApi, database: LBCDatabase, @IoDispatcher ioDispatcher: CoroutineDispatcher): BooksDataSource =
        BooksDataSourceImpl(api, database, ioDispatcher)
}