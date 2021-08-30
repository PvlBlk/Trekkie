package com.sevenzeroes.trekkieapp.core.domain.repository

import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary
import com.sevenzeroes.trekkieapp.core.domain.models.StapiResponse
import com.sevenzeroes.trekkieapp.core.domain.models.TmdbResponse

interface EpisodesRepository {

    suspend fun getSummaries(query: String?): MutableList<EpisodeSummary>

    suspend fun getEpisodeSpecifics(seasonNumber: Int, episodeNumber: Int): TmdbResponse

    suspend fun searchEpisodesByTitle(title: String?): StapiResponse

    suspend fun searchEpisodesInDb(title: String?): MutableList<EpisodeSummary>

    suspend fun getAllEpisodesFromDb(): MutableList<EpisodeSummary>

    suspend fun insert(vararg episodes: EpisodeSummary)
}