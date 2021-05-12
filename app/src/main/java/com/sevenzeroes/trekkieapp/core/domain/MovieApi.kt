package com.sevenzeroes.trekkieapp.core.domain

import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    companion object{
        private const val API_KEY = "1647b011f5c312f24c34f506438cca2e"
    }

    @GET("3/tv/655/season/{season_number}/episode/{episode_number}?api_key=$API_KEY&language=en-US")
    suspend fun getEpisodeSpecifics(@Path("season_number") season_number: Int, @Path("episode_number") episode_number : Int): Response<EpisodeEntity>

}