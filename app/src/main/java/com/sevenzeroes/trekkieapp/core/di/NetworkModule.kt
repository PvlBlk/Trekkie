package com.sevenzeroes.trekkieapp.core.di

import com.sevenzeroes.trekkieapp.core.domain.repository.RemoteDataSource
import com.sevenzeroes.trekkieapp.core.domain.repository.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRemoteDataSource() : RemoteDataSource{
        return RemoteDataSourceImpl()
    }

}