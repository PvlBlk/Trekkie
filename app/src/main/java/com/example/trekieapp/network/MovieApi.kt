package com.example.trekieapp.network

import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("3/tv/655/season/{season_number}/episode/{episode_number}?api_key=1647b011f5c312f24c34f506438cca2e")
    suspend fun getEpisodeSpecifics(
        @Path("season_number") season_number: Int, @Path("episode_number") episode_number : Int
    ): Response<JsonElement>
}