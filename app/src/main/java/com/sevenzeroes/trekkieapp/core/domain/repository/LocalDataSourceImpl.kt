package com.sevenzeroes.trekkieapp.core.domain.repository

import androidx.room.Room
import com.sevenzeroes.trekkieapp.core.data.EpisodesDatabase
import com.sevenzeroes.trekkieapp.core.TrekkieApplication
import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(): LocalDataSource {

    private val dataBase = Room.databaseBuilder(
        TrekkieApplication.instance?.applicationContext!!,
        EpisodesDatabase::class.java, "database-name"
    ).fallbackToDestructiveMigration().build()

    override suspend fun searchEpisodesInDb(title: String?): MutableList<EpisodeSummary> {
        return dataBase.EpisodesDao().searchEpisodesInDb(title)
    }

    override suspend fun getAllEpisodesFromDb(): MutableList<EpisodeSummary> {
        return dataBase.EpisodesDao().getAllEpisodesFromDb()
    }

    override suspend fun insert(vararg episodes: EpisodeSummary) {
        episodes.forEach { it.isFavorite = true }
        dataBase.EpisodesDao().insert(*episodes)
    }
}