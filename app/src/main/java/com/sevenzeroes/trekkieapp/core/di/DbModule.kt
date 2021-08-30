package com.sevenzeroes.trekkieapp.core.di

import com.sevenzeroes.trekkieapp.core.domain.repository.LocalDataSource
import com.sevenzeroes.trekkieapp.core.domain.repository.LocalDataSourceImpl
import com.sevenzeroes.trekkieapp.core.domain.repository.RemoteDataSource
import com.sevenzeroes.trekkieapp.core.domain.repository.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier


@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    fun provideRemoteDataSource() : RemoteDataSource {
        return RemoteDataSourceImpl()
    }

    @Provides
    fun provideLocalDataSource() : LocalDataSource{
        return LocalDataSourceImpl()
    }
}