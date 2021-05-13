package com.sevenzeroes.trekkieapp.core.domain.repository

import com.sevenzeroes.trekkieapp.core.domain.models.TmdbResponse
import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary
import com.sevenzeroes.trekkieapp.core.domain.models.StapiResponse
import retrofit2.Response

class EpisodesRepository(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource) {

   suspend fun  getSummaries(query: String?)
   : MutableList<EpisodeSummary> = remoteDataSource.getSummaries(query)

   suspend fun  getEpisodeSpecifics(seasonNumber: Int, episodeNumber: Int)
   : TmdbResponse = remoteDataSource.getEpisodeSpecifics(seasonNumber, episodeNumber)

   suspend fun  searchEpisodesByTitle(title: String?)
   : StapiResponse = remoteDataSource.searchEpisodesByTitle(title)

   suspend fun searchEpisodesInDb(title: String?) = localDataSource.searchEpisodesInDb(title)

   suspend fun getAllEpisodesFromDb() = localDataSource.getAllEpisodesFromDb()

   suspend fun insert(vararg episodes: EpisodeSummary) = localDataSource.insert(*episodes)
}