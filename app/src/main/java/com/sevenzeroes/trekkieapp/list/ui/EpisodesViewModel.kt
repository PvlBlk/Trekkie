package com.sevenzeroes.trekkieapp.list.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sevenzeroes.trekkieapp.list.data.EpisodeSummary
import com.sevenzeroes.trekkieapp.list.data.Utils
import com.sevenzeroes.trekkieapp.list.domain.MovieApi
import com.sevenzeroes.trekkieapp.list.domain.NetworkService
import com.sevenzeroes.trekkieapp.list.domain.Stapi

class EpisodesViewModel : ViewModel() {

    var stapi: Stapi = NetworkService.stapiRetrofitService()
    var movieApi: MovieApi = NetworkService.movieRetrofitService()

    var episodeSummaryLiveData = MutableLiveData<List<EpisodeSummary>>()
    var summaryList = mutableListOf<EpisodeSummary>()
    var isLoading = MutableLiveData<Boolean>()

    suspend fun getEpisodesWithLiveData(text : String?) {
        isLoading.postValue(true)
        loadEpisodes(text = text)
        isLoading.postValue(false)
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
        if (episodeSummary.name!=null ){
            summaryList.add(episodeSummary)
        }
        Log.d("result::", "" + episodeSummary.toString())
    }
}