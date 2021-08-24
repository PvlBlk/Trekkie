package com.sevenzeroes.trekkieapp.core.di

import com.sevenzeroes.trekkieapp.core.domain.repository.LocalDataSource
import com.sevenzeroes.trekkieapp.core.domain.repository.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    fun provideLocalDataSource() : LocalDataSource{
        return LocalDataSourceImpl()
    }
}