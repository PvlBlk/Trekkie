package com.sevenzeroes.trekkieapp.core.domain.interactors

import com.sevenzeroes.trekkieapp.core.domain.repository.EpisodesRepository

class SearchEpisodes(private val repository: EpisodesRepository) {
    suspend fun invoke (title: String?) = repository.searchEpisodesByTitle(title)
}