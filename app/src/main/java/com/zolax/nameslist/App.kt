package com.zolax.nameslist

import android.app.Application
import com.zolax.nameslist.di.AppComponent
import com.zolax.nameslist.di.DaggerAppComponent
import timber.log.Timber

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        appComponent = DaggerAppComponent.create()
    }
}