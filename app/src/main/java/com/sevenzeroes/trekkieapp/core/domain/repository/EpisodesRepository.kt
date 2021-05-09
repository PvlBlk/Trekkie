package com.sevenzeroes.trekkieapp.core.domain.repository

import com.google.gson.JsonElement
import retrofit2.Response

class EpisodesRepository(private val dataSource: DataSource) {
   suspend fun  getEpisodeSpecifics(seasonNumber: Int, episodeNumber: Int)
   : Response<JsonElement> = dataSource.getEpisodeSpecifics(seasonNumber, episodeNumber)

   suspend fun  searchEpisodesByTitle(title: String?)
   : Response<JsonElement> = dataSource.searchEpisodesByTitle(title)
}