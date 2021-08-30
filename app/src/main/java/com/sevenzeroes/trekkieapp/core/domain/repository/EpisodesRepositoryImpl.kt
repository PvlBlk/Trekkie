package com.sevenzeroes.trekkieapp.core.domain.repository

import com.sevenzeroes.trekkieapp.core.domain.models.TmdbResponse
import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary
import com.sevenzeroes.trekkieapp.core.domain.models.StapiResponse
import retrofit2.Response
import javax.inject.Inject

class EpisodesRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): EpisodesRepository {

    override suspend fun getSummaries(query: String?)
            : MutableList<EpisodeSummary> = remoteDataSource.getSummaries(query)

    override suspend fun getEpisodeSpecifics(seasonNumber: Int, episodeNumber: Int)
            : TmdbResponse = remoteDataSource.getEpisodeSpecifics(seasonNumber, episodeNumber)

    override suspend fun searchEpisodesByTitle(title: String?)
            : StapiResponse = remoteDataSource.searchEpisodesByTitle(title)

    override suspend fun searchEpisodesInDb(title: String?) = localDataSource.searchEpisodesInDb(title)

    override suspend fun getAllEpisodesFromDb() = localDataSource.getAllEpisodesFromDb()

    override suspend fun insert(vararg episodes: EpisodeSummary) = localDataSource.insert(*episodes)
}