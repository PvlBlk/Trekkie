package com.sevenzeroes.trekkieapp.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary

@Database(version = 1, entities = [EpisodeSummary::class])
abstract class EpisodesDatabase  : RoomDatabase() {

    abstract fun EpisodesDao(): EpisodesDao

}