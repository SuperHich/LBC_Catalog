package com.superhapp.lbccatalog.di.module

import android.content.Context
import androidx.room.Room
import com.superhapp.lbccatalog.MyApplication
import com.superhapp.lbccatalog.data.local.LBCDatabase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
internal class MyApplicationModule {

    @Provides
    @Singleton
    fun provideContext(application: MyApplication): Context =
        application.applicationContext

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://static.leboncoin.fr/img/shared/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): LBCDatabase =
        Room.databaseBuilder(context, LBCDatabase::class.java, "catalog.db")
            .fallbackToDestructiveMigration()
            .build()
}