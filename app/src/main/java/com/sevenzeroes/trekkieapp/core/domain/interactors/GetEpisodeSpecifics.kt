package com.sevenzeroes.trekkieapp.core.domain.interactors

import com.sevenzeroes.trekkieapp.core.domain.repository.EpisodesRepository
import javax.inject.Inject

class GetEpisodeSpecifics  @Inject constructor(private val repository: EpisodesRepository) {
    suspend fun invoke (seasonNumber: Int, episodeNumber: Int) = repository.getEpisodeSpecifics(seasonNumber, episodeNumber)
}