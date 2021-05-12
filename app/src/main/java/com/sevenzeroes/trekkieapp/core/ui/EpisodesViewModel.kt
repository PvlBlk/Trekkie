package com.sevenzeroes.trekkieapp.core.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sevenzeroes.trekkieapp.core.TrekkieApplication
import com.sevenzeroes.trekkieapp.core.domain.models.Episode
import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeEntity
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
    private val getEpisodeSpecifics = TrekkieApplication.instance?.interactors?.getEpisodeSpecifics

    suspend fun getEpisodes(text : String?) {
        fetchEpisodes(text = text)
    }

    private suspend fun fetchEpisodes(text: String?){
        episodes.value = Event.loading()
        summaryList.clear()
        val result =  searchEpisodes?.invoke(text)
        if (result?.body()!=null && result.isSuccessful){
           val list: List<Episode>? = result.body()?.episodes
           list?.forEach{
               loadEpisodesSpecifics(it.seasonNumber, it.episodeNumber, it.stardateFrom, it.stardateTo)
           }
            episodes.value = Event.success(summaryList)
        } else {
            episodes.value = Event.error("error")
        }
    }
    private suspend fun loadEpisodesSpecifics(seasonNumber: Int, episodeNumber: Int, stardateFrom: Double, stardateTo: Double) {
        val result =  getEpisodeSpecifics?.invoke(seasonNumber, episodeNumber)
        if (result?.body()!=null && result.isSuccessful){
             val specifics: EpisodeEntity? = result.body()
             val parsedDate : String? = Utils.parseDateFormat(specifics?.air_date)
             val episodeSummary = EpisodeSummary(parsedDate, specifics?.name!!,
                 specifics.overview, specifics.season_number, specifics.episode_number,
                 specifics.vote_average,
                 stardateFrom, stardateTo, specifics.still_path
             )
            summaryList.add(episodeSummary)
        }

    }

    override fun onToggle(episode: EpisodeSummary) {
        GlobalScope.launch(Dispatchers.IO) {
            insert?.invoke(episode)
        }
    }
}