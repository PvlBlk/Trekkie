package com.sevenzeroes.trekkieapp.core.domain.interactors

import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary
import com.sevenzeroes.trekkieapp.core.domain.repository.EpisodesRepository

class Insert(private val repository: EpisodesRepository) {
    suspend fun invoke(vararg episodes: EpisodeSummary) = repository.insert(*episodes)
}