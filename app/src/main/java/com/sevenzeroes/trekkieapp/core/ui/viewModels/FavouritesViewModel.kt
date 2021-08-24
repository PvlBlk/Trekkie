package com.sevenzeroes.trekkieapp.core.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevenzeroes.trekkieapp.core.TrekkieApplication
import com.sevenzeroes.trekkieapp.core.domain.interactors.GetAllEpisodesFromDb
import com.sevenzeroes.trekkieapp.core.domain.interactors.Insert
import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary
import com.sevenzeroes.trekkieapp.core.helpers.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(val insert: Insert,  val getAllEpisodesFromDb: GetAllEpisodesFromDb) : ViewModel() {

    val favoriteEpisodes = MutableLiveData<Event<MutableList<EpisodeSummary>>>()

    fun toggleFavorite(episode: EpisodeSummary) {
        viewModelScope.launch {
            insert.invoke(episode)
        }
    }

    suspend fun getAllEpisodesFromDb() {
        favoriteEpisodes.postValue(Event.loading())
        val summaries =  getAllEpisodesFromDb.invoke()
        if (summaries.isNullOrEmpty()){
            favoriteEpisodes.postValue(Event.error("Ошибка"))
        } else {
            favoriteEpisodes.postValue(Event.success(summaries))
        }
    }
}