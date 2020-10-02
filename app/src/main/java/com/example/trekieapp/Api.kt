package com.example.trekieapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {

    @POST("episode/search")
    fun getUsers(
        @Query("title") page: String
    ): Call<Users>

    @GET("5dcc147154000059009c2104")
    fun getUsersError(
        @Query("page") page: Int
    ): ResponseWrapper<Users>
}
