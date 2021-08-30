package com.sevenzeroes.trekkieapp.core.di

import com.sevenzeroes.trekkieapp.core.domain.repository.EpisodesRepository
import com.sevenzeroes.trekkieapp.core.domain.repository.EpisodesRepositoryImpl
import com.sevenzeroes.trekkieapp.core.domain.repository.LocalDataSource
import com.sevenzeroes.trekkieapp.core.domain.repository.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject


@Module
@InstallIn(SingletonComponent::class)
object EpisodesModule {

    @Provides
    fun provideEpisodesRepository(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource) : EpisodesRepository{
        return EpisodesRepositoryImpl(remoteDataSource, localDataSource)
    }
}