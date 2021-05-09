package com.sevenzeroes.trekkieapp.core.domain.interactors

import com.sevenzeroes.trekkieapp.core.domain.repository.EpisodesRepository

class GetEpisodeSpecifics(private val repository: EpisodesRepository) {
    suspend fun invoke (seasonNumber: Int, episodeNumber: Int) = repository.getEpisodeSpecifics(seasonNumber, episodeNumber)
}