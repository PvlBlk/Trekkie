package com.sevenzeroes.trekkieapp.core.domain.framework

import com.sevenzeroes.trekkieapp.core.domain.interactors.*

data class Interactors(
    val getEpisodeSpecifics: GetEpisodeSpecifics,
    val searchEpisodes: SearchEpisodes,
    val searchEpisodesInDb: SearchEpisodesInDb,
    val getAllEpisodesFromDb: GetAllEpisodesFromDb,
    val insert: Insert
)