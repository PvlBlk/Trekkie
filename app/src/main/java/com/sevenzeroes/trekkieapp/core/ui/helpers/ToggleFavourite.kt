package com.sevenzeroes.trekkieapp.core.ui.helpers

import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary

interface ToggleFavourite {
    fun onToggle(episode: EpisodeSummary)
}