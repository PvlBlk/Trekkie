package com.sevenzeroes.trekkieapp.core.domain.repository

import com.sevenzeroes.trekkieapp.core.domain.NetworkService
import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeEntity
import com.sevenzeroes.trekkieapp.core.domain.models.ResponseEntity
import retrofit2.Response

class RemoteDataSourceImpl : RemoteDataSource {

    var stapi = NetworkService.stapiRetrofitService()
    var movieApi = NetworkService.tmdbRetrofitService()

    override suspend fun getEpisodeSpecifics(
        seasonNumber: Int,
        episodeNumber: Int
    ): Response<EpisodeEntity> {
        return movieApi.getEpisodeSpecifics(seasonNumber, episodeNumber)
    }

    override suspend fun searchEpisodesByTitle(title: String?): Response<ResponseEntity> {
        return stapi.searchEpisodesByTitle(title)
    }
}