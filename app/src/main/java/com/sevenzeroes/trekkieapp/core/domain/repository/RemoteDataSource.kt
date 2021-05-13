package com.sevenzeroes.trekkieapp.core.domain.repository

import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary
import com.sevenzeroes.trekkieapp.core.domain.models.TmdbResponse
import com.sevenzeroes.trekkieapp.core.domain.models.StapiResponse
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getSummaries(title: String?): MutableList<EpisodeSummary>
    suspend fun searchEpisodesByTitle(title: String?): StapiResponse
    suspend fun getEpisodeSpecifics(seasonNumber: Int, episodeNumber: Int) : TmdbResponse
}