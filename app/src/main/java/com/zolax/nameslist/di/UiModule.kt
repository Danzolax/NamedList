package com.zolax.nameslist.di

import com.zolax.nameslist.ui.namesList.NamesListAdapter
import dagger.Module
import dagger.Provides


@Module
object UiModule {

    @Provides
    fun provideNamesListAdapter() = NamesListAdapter()
}