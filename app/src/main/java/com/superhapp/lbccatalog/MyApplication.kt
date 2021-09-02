package com.superhapp.lbccatalog

import com.superhapp.lbccatalog.di.component.DaggerMyApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerMyApplicationComponent.factory().create(this)
}