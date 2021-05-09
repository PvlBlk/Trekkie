package com.sevenzeroes.trekkieapp.core.domain.repository

import com.google.gson.JsonElement
import retrofit2.Response

interface DataSource {
    suspend fun getEpisodeSpecifics(seasonNumber: Int, episodeNumber: Int) : Response<JsonElement>
    suspend fun searchEpisodesByTitle(title: String?): Response<JsonElement>
}