package com.sevenzeroes.trekkieapp.core.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary

@Dao
interface EpisodesDao {

    @Query("SELECT * FROM EpisodeSummary WHERE name LIKE :title")
    suspend fun searchEpisodesInDb(title: String?): EpisodeSummary

    @Query("SELECT * FROM EpisodeSummary")
    suspend fun getAllEpisodesFromDb() : EpisodeSummary

    @Insert
    suspend fun insert(vararg episodes: EpisodeSummary)

}