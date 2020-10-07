package com.example.trekieapp.utils

import com.example.trekieapp.model.ResponseEntity
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject

object Utils {
    @JvmStatic
    fun parseEpisodeJson(response : JsonElement?) : ResponseEntity.Episode {
        var gson = Gson()
        response as JsonObject
        val uid = response.get("uid").asString
        val title = response.get("title").asString
        return ResponseEntity.Episode(uid, title)
    }
}