package com.sevenzeroes.trekkieapp.core.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sevenzeroes.trekkieapp.core.TrekkieApplication
import com.sevenzeroes.trekkieapp.core.domain.models.Episode
import com.sevenzeroes.trekkieapp.core.domain.models.TmdbResponse
import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary
import com.sevenzeroes.trekkieapp.core.helpers.Event
import com.sevenzeroes.trekkieapp.core.helpers.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EpisodesViewModel : ViewModel(), ToggleFavourite {

    var episodes = MutableLiveData<Event<MutableList<EpisodeSummary>>>()
    var summaryList = mutableListOf<EpisodeSummary>()
    val insert = TrekkieApplication.instance?.interactors?.insert
    val getAllEpisodesFromDb = TrekkieApplication.instance?.interactors?.getAllEpisodesFromDb
    private val searchEpisodes = TrekkieApplication.instance?.interactors?.searchEpisodes
    private val getSummaries = TrekkieApplication.instance?.interactors?.getSummaries
    private val getEpisodeSpecifics = TrekkieApplication.instance?.interactors?.getEpisodeSpecifics

    suspend fun getSummaries(query: String?){
        episodes.value = Event.loading()
        val summaries =  getSummaries?.invoke(query)
        if (summaries.isNullOrEmpty()){
            episodes.value = Event.error("Ошибка")
        } else {
            episodes.value = Event.success(summaries!!)
        }

    }


    override fun onToggle(episode: EpisodeSummary) {
        GlobalScope.launch(Dispatchers.IO) {
            insert?.invoke(episode)
        }
    }
}