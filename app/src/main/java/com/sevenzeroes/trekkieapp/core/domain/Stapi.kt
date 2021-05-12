package com.sevenzeroes.trekkieapp.core.domain

import com.sevenzeroes.trekkieapp.core.domain.models.ResponseEntity
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface Stapi {

    @POST("episode/search")
    suspend fun searchEpisodesByTitle(@Query("title") title: String?): Response<ResponseEntity>

}
