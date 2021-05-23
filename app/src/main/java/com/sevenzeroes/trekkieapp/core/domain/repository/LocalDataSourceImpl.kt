package com.sevenzeroes.trekkieapp.core.domain.repository

import androidx.room.Room
import com.sevenzeroes.trekkieapp.core.data.EpisodesDatabase
import com.sevenzeroes.trekkieapp.core.TrekkieApplication
import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary

class LocalDataSourceImpl : LocalDataSource {

    private val dataBase = Room.databaseBuilder(
        TrekkieApplication.instance?.applicationContext!!,
        EpisodesDatabase::class.java, "database-name"
    ).build()

    override suspend fun searchEpisodesInDb(title: String?): MutableList<EpisodeSummary> {
        return dataBase.EpisodesDao().searchEpisodesInDb(title)
    }

    override suspend fun getAllEpisodesFromDb(): MutableList<EpisodeSummary> {
        return dataBase.EpisodesDao().getAllEpisodesFromDb()
    }

    override suspend fun insert(vararg episodes: EpisodeSummary) {
        dataBase.EpisodesDao().insert(*episodes)
    }
}