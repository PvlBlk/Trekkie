package com.example.trekkieapp.network

import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface Stapi {

    @POST("episode/search")
    suspend fun getEpisodes(
        @Query("title") page: String?
    ): Response<JsonElement>

}
