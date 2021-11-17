package com.zolax.nameslist.di

import com.zolax.nameslist.data.repositories.BaseRepository
import com.zolax.nameslist.ui.namesList.NamesListViewModelFactory
import dagger.Module
import dagger.Provides


@Module(includes = [DataModule::class, UiModule::class])
object AppModule {


    @Provides
    fun provideNamesListViewModelFactory(baseRepository: BaseRepository): NamesListViewModelFactory {
        return NamesListViewModelFactory(baseRepository)
    }

}