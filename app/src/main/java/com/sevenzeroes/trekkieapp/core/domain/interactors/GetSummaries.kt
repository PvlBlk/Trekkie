package com.sevenzeroes.trekkieapp.core.domain.interactors

import com.sevenzeroes.trekkieapp.core.domain.repository.EpisodesRepository

class GetSummaries(private val repository: EpisodesRepository) {
    suspend fun invoke (query: String?) = repository.getSummaries(query)
}