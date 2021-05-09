package com.sevenzeroes.trekkieapp.core.domain.repository

import com.google.gson.JsonElement
import com.sevenzeroes.trekkieapp.core.domain.NetworkService
import retrofit2.Response

class RemoteDataSourceImpl : RemoteDataSource {

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