package com.sevenzeroes.trekkieapp.core.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sevenzeroes.trekkieapp.TrekkieApplication
import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary
import com.sevenzeroes.trekkieapp.core.helpers.Event
import com.sevenzeroes.trekkieapp.core.helpers.Utils

class EpisodesViewModel : ViewModel() {

    var episodes = MutableLiveData<Event<MutableList<EpisodeSummary>>>()
    var summaryList = mutableListOf<EpisodeSummary>()

    suspend fun getEpisodes(text : String?) {
        fetchEpisodes(text = text)
    }

    private suspend fun fetchEpisodes(text: String?){
        episodes.postValue(Event.loading())
        summaryList.clear()
        val result =  TrekkieApplication.instance?.interactors?.searchEpisodes?.invoke(text)
        if (result!=null && result.isSuccessful){
           val list = Utils.parseEpisodeJson(response = result.body())
           list?.forEach{
               loadEpisodesSpecifics(it.seasonNumber, it.episodeNumber, it.stardateFrom, it.stardateTo)
           }
           episodes.postValue(Event.success(summaryList))
        } else {
            episodes.postValue(Event.error("error"))
        }
    }
    private suspend fun loadEpisodesSpecifics(seasonNumber: Int, episodeNumber: Int, stardateFrom: Double, stardateTo: Double) {
        val result =  TrekkieApplication.instance?.interactors?.getEpisodeSpecifics?.invoke(seasonNumber, episodeNumber)
        if (result!=null && result.isSuccessful){

             val specifics = Utils.parseSpecificJson(result.body())
             val parsedDate : String? = Utils.parseDateFormat(specifics?.air_date)
             val episodeSummary = EpisodeSummary(parsedDate, specifics?.name, specifics?.overview, specifics?.season_number, specifics?.episode_number, specifics?.vote_average,
                 stardateFrom, stardateTo, specifics?.still_path)
             if (episodeSummary.name!=null ){
                 summaryList.add(episodeSummary)
             }
        }

    }
}