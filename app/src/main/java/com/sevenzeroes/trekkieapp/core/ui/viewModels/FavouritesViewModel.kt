package com.sevenzeroes.trekkieapp.core.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevenzeroes.trekkieapp.core.domain.interactors.GetAllEpisodesFromDb
import com.sevenzeroes.trekkieapp.core.domain.interactors.Insert
import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary
import com.sevenzeroes.trekkieapp.core.helpers.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(val insert: Insert,  val getAllEpisodesFromDb: GetAllEpisodesFromDb) : ViewModel() {

    private val _favoriteEpisodes = MutableStateFlow<Event<MutableList<EpisodeSummary>>>(Event.error(""))
    val favoriteEpisodes: StateFlow<Event<MutableList<EpisodeSummary>>> = _favoriteEpisodes

    fun toggleFavorite(episode: EpisodeSummary) {
        viewModelScope.launch {
            insert.invoke(episode)
        }
    }

    suspend fun getAllEpisodesFromDb() {
        _favoriteEpisodes.value = (Event.loading())
        val summaries =  getAllEpisodesFromDb.invoke()
        if (summaries.isNullOrEmpty()){
            _favoriteEpisodes.value = (Event.error("Ошибка"))
        } else {
            _favoriteEpisodes.value = (Event.success(summaries))
        }
    }
}