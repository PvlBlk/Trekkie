package com.sevenzeroes.trekkieapp.core.domain.interactors

import com.sevenzeroes.trekkieapp.core.domain.repository.EpisodesRepository

class GetAllEpisodesFromDb(private val repository: EpisodesRepository) {
    suspend fun invoke () = repository.getAllEpisodesFromDb()
}