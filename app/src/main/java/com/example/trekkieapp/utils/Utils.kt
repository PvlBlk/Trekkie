package com.example.trekkieapp.utils

import android.util.Log
import com.example.trekkieapp.model.Episode
import com.example.trekkieapp.model.ResponseEntity
import com.example.trekkieapp.model.SpecificsEntity
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject

object Utils {
    @JvmStatic
    fun parseEpisodeJson(response : JsonElement?) : List<Episode> {
        response as JsonObject?
        val result = Gson().fromJson(response, ResponseEntity::class.java)
        Log.d("Utils::", "" + result.toString())
        return result.episodes
    }
    @JvmStatic
    fun parseSpecificJson(response : JsonElement?) : SpecificsEntity {
        response as JsonObject?
        val result = Gson().fromJson(response, SpecificsEntity::class.java)
        Log.d("Utils::", "" + result.toString())
        return result
    }
}