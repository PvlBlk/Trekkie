package com.sevenzeroes.trekkieapp.core.domain.framework

import com.google.gson.JsonElement
import com.sevenzeroes.trekkieapp.core.domain.NetworkService
import com.sevenzeroes.trekkieapp.core.domain.repository.DataSource
import retrofit2.Response

class RemoteDataSource : DataSource {

    var stapi = NetworkService.stapiRetrofitService()
    var movieApi = NetworkService.tmdbRetrofitService()

    override suspend fun getEpisodeSpecifics(
        seasonNumber: Int,
        episodeNumber: Int
    ): Response<JsonElement> {
        return movieApi.getEpisodeSpecifics(seasonNumber, episodeNumber)
    }

    override suspend fun searchEpisodesByTitle(title: String?): Response<JsonElement> {
        return stapi.searchEpisodesByTitle(title)
    }
}