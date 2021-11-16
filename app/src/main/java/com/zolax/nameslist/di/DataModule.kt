package com.zolax.nameslist.di

import com.zolax.nameslist.data.repositories.BaseRepository
import com.zolax.nameslist.data.repositories.BaseRepositoryimpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Provides
    fun provideBaseRepositoryImpl() = BaseRepositoryimpl()


    @Binds
    fun bindBaseRepository(baseRepositoryimpl: BaseRepositoryimpl): BaseRepository


}