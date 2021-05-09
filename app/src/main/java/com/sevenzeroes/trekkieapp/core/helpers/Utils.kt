package com.sevenzeroes.trekkieapp.core.helpers

import android.os.Build
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.sevenzeroes.trekkieapp.core.domain.models.Episode
import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeEntity
import com.sevenzeroes.trekkieapp.core.domain.models.ResponseEntity
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Utils {

    fun parseEpisodeJson(response : JsonElement?) : List<Episode>? {
        response as JsonObject?
        val result = Gson().fromJson(response, ResponseEntity::class.java)
        Log.d("Utils::", "" + result.toString())
        return result.episodes
    }

    fun parseSpecificJson(response : JsonElement?) : EpisodeEntity? {
        response as JsonObject?
        val result : EpisodeEntity? = Gson().fromJson(response, EpisodeEntity::class.java)
        Log.d("Utils::", "" + result.toString())
        return result
    }

    fun parseDateFormat(dateString: String?): String? {
        if (Build.VERSION.SDK_INT >= 26) {
            if (dateString!=null){
            val parsedDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE)
                var formatter = DateTimeFormatter.ofPattern("d MMM uuuu")
                return formatter.format(parsedDate)
            }
            return "n/a"

        } else {
            val parser = SimpleDateFormat("yyyy-MM-dd")
            val formatter = SimpleDateFormat("d MMM uuuu")
            val formattedDate = formatter.format(parser.parse(dateString))
            return formattedDate
        }
    }
}