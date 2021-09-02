package com.superhapp.lbccatalog.di.component

import com.superhapp.lbccatalog.MyApplication
import com.superhapp.lbccatalog.di.module.BookBuilder
import com.superhapp.lbccatalog.di.module.DispatcherModule
import com.superhapp.lbccatalog.di.module.MyApplicationModule
import com.superhapp.lbccatalog.di.module.ViewModelBuilders
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    MyApplicationModule::class,
    ViewModelBuilders::class,
    BookBuilder::class,
    DispatcherModule::class
])
internal interface MyApplicationComponent : AndroidInjector<MyApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: MyApplication): MyApplicationComponent
    }
}