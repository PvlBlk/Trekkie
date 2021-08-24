package com.sevenzeroes.trekkieapp.core.domain.interactors

import com.sevenzeroes.trekkieapp.core.domain.repository.EpisodesRepository
import javax.inject.Inject

class SearchEpisodesInDb  @Inject constructor(private val repository: EpisodesRepository) {
    suspend fun invoke (title: String?) = repository.searchEpisodesInDb(title)
}