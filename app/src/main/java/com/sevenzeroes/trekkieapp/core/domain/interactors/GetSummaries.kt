package com.sevenzeroes.trekkieapp.core.domain.interactors

import com.sevenzeroes.trekkieapp.core.domain.repository.EpisodesRepository
import javax.inject.Inject

class GetSummaries  @Inject constructor(private val repository: EpisodesRepository) {
    suspend fun invoke (query: String?) = repository.getSummaries(query)
}