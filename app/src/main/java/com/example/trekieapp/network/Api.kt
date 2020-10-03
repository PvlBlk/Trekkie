package com.example.trekieapp.network

import com.example.trekieapp.Episode
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {

    @POST("episode/search")
    fun getEpisodes(
        @Query("title") page: String
    ): Call<Episode>


}
