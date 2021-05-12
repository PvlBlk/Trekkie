package com.sevenzeroes.trekkieapp.core.domain.repository

import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeEntity
import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary
import com.sevenzeroes.trekkieapp.core.domain.models.ResponseEntity
import retrofit2.Response

class EpisodesRepository(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource) {
   suspend fun  getEpisodeSpecifics(seasonNumber: Int, episodeNumber: Int)
   : Response<EpisodeEntity> = remoteDataSource.getEpisodeSpecifics(seasonNumber, episodeNumber)

   suspend fun  searchEpisodesByTitle(title: String?)
   : Response<ResponseEntity> = remoteDataSource.searchEpisodesByTitle(title)

   suspend fun searchEpisodesInDb(title: String?) = localDataSource.searchEpisodesInDb(title)

   suspend fun getAllEpisodesFromDb() = localDataSource.getAllEpisodesFromDb()

   suspend fun insert(vararg episodes: EpisodeSummary) = localDataSource.insert(*episodes)
}