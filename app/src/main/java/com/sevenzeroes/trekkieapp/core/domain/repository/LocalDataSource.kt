package com.sevenzeroes.trekkieapp.core.domain.repository

import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary

interface LocalDataSource {
    suspend fun searchEpisodesInDb(title: String?): EpisodeSummary

    suspend fun getAllEpisodesFromDb() : EpisodeSummary

    suspend fun insert(vararg episodes: EpisodeSummary)
}