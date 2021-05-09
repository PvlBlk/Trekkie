package com.sevenzeroes.trekkieapp.core.domain.repository

import com.google.gson.JsonElement
import com.sevenzeroes.trekkieapp.core.data.EpisodesDao
import retrofit2.Response

class EpisodesRepository(private val remoteDataSource: RemoteDataSource) {
   suspend fun  getEpisodeSpecifics(seasonNumber: Int, episodeNumber: Int)
   : Response<JsonElement> = remoteDataSource.getEpisodeSpecifics(seasonNumber, episodeNumber)

   suspend fun  searchEpisodesByTitle(title: String?)
   : Response<JsonElement> = remoteDataSource.searchEpisodesByTitle(title)

//   suspend fun searchEpisodesInDb(title: String?) = EpisodesDao.searchEpisodesInDb todo
}