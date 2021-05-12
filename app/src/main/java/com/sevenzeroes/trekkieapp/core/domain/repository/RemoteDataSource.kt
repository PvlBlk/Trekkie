package com.sevenzeroes.trekkieapp.core.domain.repository

import com.google.gson.JsonElement
import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeEntity
import com.sevenzeroes.trekkieapp.core.domain.models.ResponseEntity
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getEpisodeSpecifics(seasonNumber: Int, episodeNumber: Int) : Response<EpisodeEntity>
    suspend fun searchEpisodesByTitle(title: String?): Response<ResponseEntity>
}