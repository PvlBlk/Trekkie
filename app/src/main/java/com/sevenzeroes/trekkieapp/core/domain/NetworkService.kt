package com.sevenzeroes.trekkieapp.core.domain

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {

    private const val STAPI_BASE_URL = "http://stapi.co/api/v1/rest/"
    private const val TMDB_BASE_URL = "https://api.themoviedb.org/"


    fun stapiRetrofitService(): Stapi {
        return Retrofit.Builder()
            .baseUrl(STAPI_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Stapi::class.java)
    }

    fun tmdbRetrofitService(): TmdbApi {
        return Retrofit.Builder()
            .baseUrl(TMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TmdbApi::class.java)
    }

}