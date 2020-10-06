package com.example.trekieapp.utils

import com.example.trekieapp.model.ResponseEntity
import com.google.gson.Gson
import com.google.gson.JsonElement

object Utils {
    @JvmStatic
    fun parseEpisodeJson(response : JsonElement){
        var gson = Gson()
        var episodeEntity = gson?.fromJson(response, ResponseEntity.Episode::class.java) //todo
    }
}