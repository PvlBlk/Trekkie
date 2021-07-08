package com.sevenzeroes.trekkieapp.core.domain.interactors

data class Interactors(
    val getSummaries: GetSummaries,
    val getEpisodeSpecifics: GetEpisodeSpecifics,
    val searchEpisodes: SearchEpisodes,
    val searchEpisodesInDb: SearchEpisodesInDb,
    val getAllEpisodesFromDb: GetAllEpisodesFromDb,
    val insert: Insert
)