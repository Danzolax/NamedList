package com.zolax.nameslist.di

import com.zolax.nameslist.data.repositories.BaseRepository
import com.zolax.nameslist.data.repositories.BaseRepositoryimpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [DataBindModule::class])
class DataModule {

    @Provides
    fun provideBaseRepositoryImpl() = BaseRepositoryimpl()





}

@Module
interface DataBindModule{
    @Binds
    fun bindBaseRepository(baseRepositoryimpl: BaseRepositoryimpl): BaseRepository
}