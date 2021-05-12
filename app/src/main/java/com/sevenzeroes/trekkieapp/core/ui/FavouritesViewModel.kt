package com.sevenzeroes.trekkieapp.core.ui

import androidx.lifecycle.ViewModel
import com.sevenzeroes.trekkieapp.core.TrekkieApplication
import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavouritesViewModel : ViewModel(), ToggleFavourite {

    val insert = TrekkieApplication.instance?.interactors?.insert
    val getAllEpisodesFromDb = TrekkieApplication.instance?.interactors?.getAllEpisodesFromDb

    override fun onToggle(episode: EpisodeSummary) {
        GlobalScope.launch {
            insert?.invoke(episode)
        }
    }

}