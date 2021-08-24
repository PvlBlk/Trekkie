package com.sevenzeroes.trekkieapp.core.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevenzeroes.trekkieapp.core.TrekkieApplication
import com.sevenzeroes.trekkieapp.core.domain.interactors.GetSummaries
import com.sevenzeroes.trekkieapp.core.domain.interactors.Insert
import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary
import com.sevenzeroes.trekkieapp.core.helpers.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(val insert: Insert, val getSummaries: GetSummaries) :
    ViewModel() {

    var episodes = MutableLiveData<Event<MutableList<EpisodeSummary>>>()

    suspend fun getSummaries(query: String?) {
        episodes.value = Event.loading()
        val summaries = getSummaries.invoke(query)
        if (summaries.isNullOrEmpty()) {
            episodes.value = Event.error("Ошибка")
        } else {
            episodes.value = Event.success(summaries)
        }

    }

    fun toggleFavorite(episode: EpisodeSummary) {
        viewModelScope.launch(Dispatchers.IO) {
            insert.invoke(episode)
        }
    }
}