package com.sevenzeroes.trekkieapp.core.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevenzeroes.trekkieapp.core.TrekkieApplication
import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary
import com.sevenzeroes.trekkieapp.core.helpers.Event
import com.sevenzeroes.trekkieapp.core.ui.helpers.ToggleFavourite
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavouritesViewModel : ViewModel(), ToggleFavourite {

    val favoriteEpisodes = MutableLiveData<Event<MutableList<EpisodeSummary>>>()
    val insert = TrekkieApplication.instance?.interactors?.insert
    private val getAllEpisodesFromDb = TrekkieApplication.instance?.interactors?.getAllEpisodesFromDb

    override fun onToggleFavorite(episode: EpisodeSummary) {
        viewModelScope.launch {
            insert?.invoke(episode)
        }
    }

    suspend fun getAllEpisodesFromDb() {
        favoriteEpisodes.postValue(Event.loading())
        val summaries =  getAllEpisodesFromDb?.invoke()
        if (summaries.isNullOrEmpty()){
            favoriteEpisodes.postValue(Event.error("Ошибка"))
        } else {
            favoriteEpisodes.postValue(Event.success(summaries))
        }
    }
}