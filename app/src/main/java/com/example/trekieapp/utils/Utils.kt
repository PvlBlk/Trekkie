package com.example.trekieapp.utils

import android.util.Log
import com.example.trekieapp.model.Episode
import com.example.trekieapp.model.ResponseEntity
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject

object Utils {
    @JvmStatic
    fun parseEpisodeJson(response : JsonElement?) : List<Episode> {
        var gson = Gson()
        response as JsonObject?
        val result = Gson().fromJson(response, ResponseEntity::class.java)
        return result.episodes
        Log.d("Utils::", "" + result.toString())
    }
}