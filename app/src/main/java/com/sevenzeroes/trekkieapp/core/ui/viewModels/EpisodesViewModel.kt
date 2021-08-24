package com.sevenzeroes.trekkieapp.core.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevenzeroes.trekkieapp.core.domain.interactors.GetSummaries
import com.sevenzeroes.trekkieapp.core.domain.interactors.Insert
import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary
import com.sevenzeroes.trekkieapp.core.helpers.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(val insert: Insert, val getSummaries: GetSummaries) :
    ViewModel() {

    private val _episodes = MutableStateFlow<Event<MutableList<EpisodeSummary>>>(Event.error(""))
    val episodes: StateFlow<Event<MutableList<EpisodeSummary>>> = _episodes

    suspend fun getSummaries(query: String?) {
        _episodes.value = Event.loading()
        val summaries = getSummaries.invoke(query)
        if (summaries.isNullOrEmpty()) {
            _episodes.value = Event.error("Ошибка")
        } else {
            _episodes.value = Event.success(summaries)
        }

    }

    fun toggleFavorite(episode: EpisodeSummary) {
        viewModelScope.launch(Dispatchers.IO) {
            insert.invoke(episode)
        }
    }
}