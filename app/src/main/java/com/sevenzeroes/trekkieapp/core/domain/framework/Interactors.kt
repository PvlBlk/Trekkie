package com.sevenzeroes.trekkieapp.core.domain.framework

import com.sevenzeroes.trekkieapp.core.domain.interactors.GetEpisodeSpecifics
import com.sevenzeroes.trekkieapp.core.domain.interactors.SearchEpisodes

data class Interactors(
    val getEpisodeSpecifics: GetEpisodeSpecifics,
    val searchEpisodes: SearchEpisodes
)