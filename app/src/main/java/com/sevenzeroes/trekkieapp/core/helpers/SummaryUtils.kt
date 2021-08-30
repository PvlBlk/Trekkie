package com.sevenzeroes.trekkieapp.core.helpers

import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary

fun EpisodeSummary.getSeasonEpisode() = "s0" + this.season_number + "e" + this.episode_number
