package com.example.trekkieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trekkieapp.model.EpisodeSummary
import com.example.trekkieapp.utils.Utils

class ActivityViewModel : BaseViewModel() {

    var episodeSummaryLiveData = MutableLiveData<List<EpisodeSummary>>()
    var summaryList = mutableListOf<EpisodeSummary>()
    suspend fun getEpisodesWithLiveData(text : String?) {
        loadEpisodes(text = text)
    }

    private suspend fun loadEpisodes(text: String?){
        summaryList.clear()
        val result = stapi.getEpisodes(text)
        Log.d("result::", "" +  result.body())
        val list = Utils.parseEpisodeJson(response = result.body())
        list?.forEach{
            loadEpisodesSpecifics(it.seasonNumber, it.episodeNumber, it.stardateFrom, it.stardateTo)
        }
        episodeSummaryLiveData.postValue(summaryList)
    }
    private suspend fun loadEpisodesSpecifics(seasonNumber: Int, episodeNumber: Int, stardateFrom: Double, stardateTo: Double) {
        val result = movieApi.getEpisodeSpecifics(seasonNumber, episodeNumber)
        val specifics = Utils.parseSpecificJson(result.body())
        var parsedDate : String? = Utils.parseDateFormat(specifics?.air_date)
        var episodeSummary = EpisodeSummary(parsedDate, specifics?.name, specifics?.overview, specifics?.season_number, specifics?.episode_number, specifics?.vote_average,
            stardateFrom, stardateTo, specifics?.still_path)
        summaryList.add(episodeSummary)
        Log.d("result::", "" + episodeSummary.toString())
    }


}