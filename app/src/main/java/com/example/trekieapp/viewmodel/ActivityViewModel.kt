package com.example.trekieapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.trekieapp.model.Episode
import com.example.trekieapp.model.EpisodeSummary
import com.example.trekieapp.utils.Utils

class ActivityViewModel : BaseViewModel() {

    var episodeSummaryLiveData = MutableLiveData<List<EpisodeSummary>>()
    var summaryList = mutableListOf<EpisodeSummary>()
    suspend fun getEpisodesWithLiveData(text : String) {
        loadEpisodes(text = text)
    }

    suspend fun loadEpisodes(text: String){

        val result = stapi.getEpisodes(text)
        val list = Utils.parseEpisodeJson(response = result.body())
        list.forEach{
            loadEpisodesSpecifics(it.seasonNumber, it.episodeNumber)
        }
        episodeSummaryLiveData.postValue(summaryList)
        //Log.d("result::", "" + Utils.parseEpisodeJson(response = result.body()))
    }
    suspend fun loadEpisodesSpecifics(seasonNumber: Int, episodeNumber: Int) {
        val result = movieApi.getEpisodeSpecifics(seasonNumber, episodeNumber)
        val specifics = Utils.parseSpecificJson(result.body())
        var episodeSummary = EpisodeSummary(specifics.air_date, specifics.name, specifics.overview, specifics.season_number, specifics.episode_number, specifics.vote_average)
        summaryList.add(episodeSummary)
        Log.d("result::", "" + episodeSummary.toString())
    }

}