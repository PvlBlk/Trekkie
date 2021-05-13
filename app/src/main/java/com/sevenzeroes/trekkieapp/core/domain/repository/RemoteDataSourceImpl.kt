package com.sevenzeroes.trekkieapp.core.domain.repository

import com.sevenzeroes.trekkieapp.core.domain.NetworkService
import com.sevenzeroes.trekkieapp.core.domain.models.Episode
import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary
import com.sevenzeroes.trekkieapp.core.domain.models.StapiResponse
import com.sevenzeroes.trekkieapp.core.domain.models.TmdbResponse
import com.sevenzeroes.trekkieapp.core.helpers.Utils

class RemoteDataSourceImpl : RemoteDataSource {

    var stapi = NetworkService.stapiRetrofitService()
    var movieApi = NetworkService.tmdbRetrofitService()

    override suspend fun getSummaries(title: String?) : MutableList<EpisodeSummary> {
        val stapiResponse = searchEpisodesByTitle(title)
        val summaryList = mutableListOf<EpisodeSummary>()
        val list: List<Episode> = stapiResponse.episodes
        list.forEach {
            val tmdbResponse = getEpisodeSpecifics(it.seasonNumber, it.episodeNumber)
            val parsedDate : String? = Utils.parseDateFormat(tmdbResponse.air_date)
            val episodeSummary = EpisodeSummary(parsedDate, tmdbResponse.name!!,
                tmdbResponse.overview, tmdbResponse.season_number, tmdbResponse.episode_number,
                tmdbResponse.vote_average,
                it.stardateFrom, it.stardateTo, tmdbResponse.still_path
            )
            summaryList.add(episodeSummary)
        }
        return summaryList
    }

    override suspend fun searchEpisodesByTitle(title: String?): StapiResponse {
        return stapi.searchEpisodesByTitle(title)
    }

    override suspend fun getEpisodeSpecifics(seasonNumber: Int, episodeNumber: Int): TmdbResponse {
        return movieApi.getEpisodeSpecifics(seasonNumber, episodeNumber)
    }


}