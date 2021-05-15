package com.sevenzeroes.trekkieapp.core.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary

@Dao
interface EpisodesDao {

    @Query("SELECT * FROM EpisodeSummary WHERE name LIKE :title")
    suspend fun searchEpisodesInDb(title: String?): MutableList<EpisodeSummary>

    @Query("SELECT * FROM EpisodeSummary")
    suspend fun getAllEpisodesFromDb() : MutableList<EpisodeSummary>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg episodes: EpisodeSummary)

}