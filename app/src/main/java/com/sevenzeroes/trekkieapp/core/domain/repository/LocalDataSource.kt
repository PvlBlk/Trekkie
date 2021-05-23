package com.sevenzeroes.trekkieapp.core.domain.repository

import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary

interface LocalDataSource {
    suspend fun searchEpisodesInDb(title: String?): MutableList<EpisodeSummary>

    suspend fun getAllEpisodesFromDb() : MutableList<EpisodeSummary>

    suspend fun insert(vararg episodes: EpisodeSummary)
}