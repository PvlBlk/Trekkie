package com.sevenzeroes.trekkieapp.core.helpers

import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary

fun EpisodeSummary.getId() = "s0" + this.season_number + "e" + this.episode_number
