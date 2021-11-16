package com.zolax.nameslist.di

import androidx.lifecycle.ViewModel
import com.zolax.nameslist.data.repositories.BaseRepository
import com.zolax.nameslist.ui.namesList.NamesListViewModel
import com.zolax.nameslist.ui.namesList.NamesListViewModelFactory
import dagger.Module
import dagger.Provides


@Module(includes = [DataModule::class])
object AppModule {


    @Provides
    fun provideNamesListViewModelFactory(baseRepository: BaseRepository): NamesListViewModelFactory {
        return NamesListViewModelFactory(baseRepository)
    }

}