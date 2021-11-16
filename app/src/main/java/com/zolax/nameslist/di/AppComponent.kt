package com.zolax.nameslist.di

import com.zolax.nameslist.ui.namesList.NamesListActivity
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: NamesListActivity)
}