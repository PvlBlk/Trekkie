package com.sevenzeroes.trekkieapp.core.domain.interactors

import com.sevenzeroes.trekkieapp.core.domain.repository.EpisodesRepository
import javax.inject.Inject

class GetAllEpisodesFromDb  @Inject constructor(private val repositoryImpl: EpisodesRepository) {
    suspend fun invoke () = repositoryImpl.getAllEpisodesFromDb()
}